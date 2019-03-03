package com.tian.thread.model.future.impl;

import com.tian.thread.model.future.CallBack;
import com.tian.thread.model.future.Future;
import com.tian.thread.model.future.FutureService;
import com.tian.thread.model.future.Task;

public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    @Override
    public Future<?> submmit(Runnable runnable) {

        FutureTask<Void> future = new FutureTask<>();
        new Thread(() ->
        {
            runnable.run();//调用方法
            future.finish(null);

        }).start();

        return future;
    }

    @Override
    public Future<OUT> submmit(Task<IN, OUT> task, IN input, CallBack<OUT> back) {

        FutureTask<OUT> future = new FutureTask<>();
        new Thread(() ->
        {
            OUT result = task.get(input);//调用方法
            future.finish(result);
            if(back != null){
                back.call(result);
            }

        }).start();

        return future;
    }
}
