package com.tian.thread.model.future;

import com.tian.thread.model.future.impl.FutureServiceImpl;

public interface FutureService<IN, OUT> {

    /**
     * 提交不需要返回结果
     */
    Future<?> submmit(Runnable runnable);

    /**
     * 提交会返回结果
     */
    Future<OUT> submmit(Task<IN, OUT> task, IN input, CallBack<OUT> back);

    /**
     * 构造实例
     */
    static <T, R> FutureService<T, R> newService()
    {
        return new FutureServiceImpl<>();

    }


}
