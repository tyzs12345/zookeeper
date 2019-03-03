package com.tian.thread.model.future;
@FunctionalInterface
public interface CallBack<T> {

    void call(T t);
}
