package com.gewara.piiic.task;

/**
 * Created by user on 2016/1/26.
 */

public abstract interface BackgroundExecutor
{
    public abstract <T> void execute(BackgroundTask<T> paramBackgroundTask);
}