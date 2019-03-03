package com.tian.thread.model.future;

import com.tian.thread.model.future.impl.FutureTask;

import java.util.concurrent.TimeUnit;

public class test {

    public static void main(String args[])throws Exception{

       /* FutureService<Void, Void> service = FutureService.newService();
        Future<?> futrue = service.submmit(() ->
        {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("111111111111111111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        futrue.get();
        System.out.println("ddddddddddddddddddddd");*/

        FutureService<String, Integer> service1 = FutureService.newService();
        Future<Integer> futrue1 = service1.submmit(input1 -> //返回结果
        {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("111111111111111111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input1.length();
        }, "hello", (param)->
             {
                 boolean dsds = param.equals(5);
                 System.out.println(dsds);
             }
        );


       /* Integer result = futrue1.get();
        System.out.println("result: "+ result);*/




    }
}
