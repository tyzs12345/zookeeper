package com.tian.thread.model.future;

public interface Future<T> {

    /**
     * 获取结果
     */
    T get() throws InterruptedException;

    /**
     * 判断事件是否完成
     */
    boolean done();

    public void finish(T result);

}
