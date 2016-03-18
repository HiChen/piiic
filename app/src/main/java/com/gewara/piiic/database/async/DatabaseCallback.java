package com.gewara.piiic.database.async;

/**
 * Created by user on 2016/1/26.
 */

public abstract interface DatabaseCallback<T>
{
    public abstract void onCompleted(T paramT);
}
