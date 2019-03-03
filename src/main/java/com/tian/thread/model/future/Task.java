package com.tian.thread.model.future;
@FunctionalInterface
public interface Task<IN, OUT> {

    OUT get(IN input);

}
