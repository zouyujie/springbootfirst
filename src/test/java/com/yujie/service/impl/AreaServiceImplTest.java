package com.yujie.service.impl;

import com.yujie.service.AreaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceImplTest {
    @Autowired
    public AreaService areaService;
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void selectAllArea() throws InterruptedException {
        final CountDownLatch latch= new CountDownLatch(10);//使用java并发库concurrent
        //启用5个线程
        for(int i=1;i<=10;i++){
            new Thread(new Runnable(){
                public void run(){
                    try {
                        //Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    areaService.selectAllArea();
                    System.out.println(String.format("子线程%s执行！",Thread.currentThread().getName()));
                    latch.countDown();//让latch中的数值减一
                }
            }).start();
        }
        //主线程
        latch.await();//阻塞当前线程直到latch中数值为零才执行
        System.out.println("主线程执行！");
    }
    @Test
    public void selectAllArea1() throws InterruptedException {
        final CountDownLatch latch= new CountDownLatch(10);//使用java并发库concurrent
        //启用5个线程
        for(int i=1;i<=100;i++){
            new Thread(new Runnable(){
                public void run(){
                    try {
                        //Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    areaService.selectAllArea();
                    System.out.println(String.format("子线程%s执行！",Thread.currentThread().getName()));
                    latch.countDown();//让latch中的数值减一
                }
            }).start();
        }
        //主线程
        latch.await();//阻塞当前线程直到latch中数值为零才执行
        System.out.println("主线程执行！");
    }
}