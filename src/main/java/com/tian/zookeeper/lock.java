package com.tian.zookeeper;

public class lock {

    public static void main(String args[]) throws InterruptedException {


        new Thread( new Runnable(){
            @Override
            public void run() {

                try {
                    Thread.sleep(4000);
                    System.out.println("================");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }){

        }.start();
        System.out.println("111111111111111111111");
        //Thread.sleep(Integer.MAX_VALUE);

    }


}
