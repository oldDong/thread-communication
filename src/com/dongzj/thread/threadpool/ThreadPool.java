package com.dongzj.thread.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/2/1
 * Time: 16:19
 */
public class ThreadPool {

    private static void executorService() throws Exception {
        BlockingQueue queue = new LinkedBlockingQueue(10);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,5,1,TimeUnit.MILLISECONDS, queue, new ThreadPoolExecutor.AbortPolicy());
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("running");
                try {
                    Thread.sleep(3000);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("running2");
                try {
                    Thread.sleep(2000);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        poolExecutor.shutdown();
        while (!poolExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程还在执行...");
        }

        System.out.println("main over");
    }

    public static void main(String[] args) {
        try {
            executorService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
