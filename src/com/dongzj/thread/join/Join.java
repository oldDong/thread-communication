package com.dongzj.thread.join;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/2/1
 * Time: 15:34
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("running");
                try {
                    Thread.sleep(3000);
                    System.out.println("thread1 over");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("running2");
                try {
                    Thread.sleep(4000);
                    System.out.println("thread2 over");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        //等待线程1终止
        t1.join();
        //等待线程2终止
        t2.join();

        System.out.println("main over");
    }
}
