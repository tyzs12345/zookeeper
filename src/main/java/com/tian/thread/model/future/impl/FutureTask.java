package com.tian.thread.model.future.impl;

import com.tian.thread.model.future.Future;

public class FutureTask<T> implements Future<T> {

    private T result; //计算结果
    private boolean isDone; //任务是否完成
    private final Object LOCK = new Object(); //定义所对象



    @Override
    public T get() throws InterruptedException
    {
        synchronized (LOCK){
            if(!isDone){
               LOCK.wait();
            }
            return result;
        }
    }

    @Override
    public void finish(T result)
    {
        synchronized (LOCK){
            if(isDone){
                return;
            }
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }

    @Override
    public boolean done() {
        return isDone;
    }
}
