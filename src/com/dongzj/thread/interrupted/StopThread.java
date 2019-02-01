package com.dongzj.thread.interrupted;

import java.util.concurrent.TimeUnit;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/2/1
 * Time: 16:03
 */
public class StopThread implements Runnable{

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getName() + "运行中...");
        }
        System.out.println(Thread.currentThread().getName() + "退出...");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread(), "thread A");
        thread.start();

        System.out.println("main线程正在运行");

        TimeUnit.MILLISECONDS.sleep(10);
        thread.interrupt();
    }
}
