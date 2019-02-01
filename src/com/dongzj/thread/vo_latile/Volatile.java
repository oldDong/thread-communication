package com.dongzj.thread.vo_latile;

import java.util.concurrent.TimeUnit;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/2/1
 * Time: 15:41
 */
public class Volatile implements Runnable {

    private static volatile boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName() + "正在运行...");
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile vo = new Volatile();
        new Thread(vo, "thread A").start();

        System.out.println("main 线程正在运行");

        TimeUnit.MICROSECONDS.sleep(100);
        vo.stopThread();
    }

    private void stopThread() {
        flag = false;
    }
}
