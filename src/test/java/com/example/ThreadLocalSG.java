package com.example;

import org.junit.Test;


public class ThreadLocalSG {
    @Test
    public void ttsc(){
  ThreadLocal tl = new ThreadLocal();
    new Thread(()->{
        tl.set("多对多");
        System.out.println(Thread.currentThread().getName()+":"+tl.get());
        System.out.println(Thread.currentThread().getName()+":"+tl.get());
        System.out.println(Thread.currentThread().getName()+":"+tl.get());
    },"对党的").start();

        new Thread(()->{
            tl.set("少对少");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
 System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"帝国的").start();

    }

}









