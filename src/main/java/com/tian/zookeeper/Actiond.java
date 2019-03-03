package com.tian.zookeeper;

@FunctionalInterface
public interface Actiond<T> {
    public void execute(T t);
}