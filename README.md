线程通信机制

    子线程执行完毕通知主线程处理某些逻辑或者是线程A在执行到某个条件通知线程B执行某个操作
    
1、等待通知机制

    两个线程通过对同一对象调用等待wait()和通知notify()方法来进行通讯
    
    wait()、notify()注意点
    1、wait()、notify()、notifyAll()调用的前提都是获得了对象的锁（Monitor）
    2、调用wait()方法后会释放锁，进入waiting状态，该线程也会被移动到等待队列中
    3、调用notify()方法会将等待队列中的线程移动到同步队列中，线程状态也会更新为blocked
    4、从wait()方法返回的前提是调用notify()方法的线程释放锁，wait()方法的线程获得锁
    
2、join()

    核心逻辑
    while (isAlive()) {
         wait(delay);          
    }
    
    join()也是利用等待通知机制，join线程完成后会调用notifyAll()方法，是在JVM实现中调用
    
3、volatile 共享内存
    
    利用volatile修饰的内存可见性
    
4、CountDownLatch

    基于AQS(AbstractQueuedSynchronizer)实现
    1、初始化一个CountDownLatch时告诉并发的线程，然后在每个线程处理完毕之后调用countDown()方法
    2、该方法会将AQS内置的一个state状态-1
    3、最终在主线程调用await()方法，它会阻塞直到state == 0的时候返回
    
5、CyclicBarrier

    中文名称为屏障或者是栅栏，也可以用于线程间通信
    它可以等待N个线程都达到某个状态后继续运行的效果
    1、首先初始化线程参与者
    2、调用await()将会在所有参与者线程都调用之前等待
    3、直到所有参与者都调用了await()后，所有线程从await()返回继续后续逻辑
    
6、线程响应中断
    
    采用中断线程的方式来通信，调用了thread.interrupt()方法其实就是将thread中的一个标志属性置为了true
    并不是说调用了该方法就可以中断线程，如果不对这个标志进行响应其实是没有什么作用
    但是如果抛出了InterruptedExection异常，该标志就会被JVM重置为false
    
7、线程池awaitTermination()方法

    使用这个方法的前提需要关闭线程池，如调用了shutdown()方法
    调用了shutdown()之后线程池会停止接受新任务，并且会平滑的关闭线程池中现有的任务
    
8、管道通信

    PipedWriter/PipedReader