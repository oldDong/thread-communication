package com.dongzj.thread.piped;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/2/1
 * Time: 16:24
 */
public class Piped {

    public static void piped() throws IOException {
        //面向于字符PipedInputStream
        //面向于字节
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();

        //输入输出流建立连接
        writer.connect(reader);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("running");
                try {
                    for (int i = 0; i < 10; i++) {
                        writer.write(i + "");
                        Thread.sleep(10);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("running2...");
                int msg = 0;
                try {
                    while ((msg = reader.read()) != -1) {
                        System.out.println("msg=" + (char)msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        try {
            piped();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
