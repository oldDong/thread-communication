package com.dongzj.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/2/1
 * Time: 15:48
 */
public class CDLatch {

    public static void main(String[] args) throws InterruptedException {
        int thread = 3;
        long start = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < thread; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread run");
                    try {
                        Thread.sleep(2000);
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        countDownLatch.await();
        long stop = System.currentTimeMillis();
        System.out.println("main over total time=" + (stop - start));
    }
}
