package com.gewara.piiic.task;

/**
 * Created by user on 2016/1/26.
 */
public abstract class BackgroundTask<T> {
    public void postExecute(T paramT) {
    }

    public void preExecute() {
    }

    public abstract T runTask();
}
