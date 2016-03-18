package com.gewara.piiic.database.async;

/**
 * Created by user on 2016/1/26.
 */

import java.util.Collection;
import java.util.List;

public abstract interface AsyncDatabaseHelper<T>
{
    public abstract void delete(DatabaseCallback<Long> paramDatabaseCallback, String paramString, String[] paramArrayOfString);

    public abstract void delete(T paramT, DatabaseCallback<Boolean> paramDatabaseCallback);

    public abstract void getCount(DatabaseCallback<Long> paramDatabaseCallback, String paramString, String[] paramArrayOfString);

    public abstract void insert(T paramT, DatabaseCallback<Long> paramDatabaseCallback);

    public abstract void insertBatch(Collection<T> paramCollection, DatabaseCallback<Boolean> paramDatabaseCallback);

    public abstract void insertOrUpdate(DatabaseCallback<T> paramDatabaseCallback, T paramT, String paramString, String[] paramArrayOfString);

    public abstract void query(DatabaseCallback<List<T>> paramDatabaseCallback, String paramString, String[] paramArrayOfString);

    public abstract void queryById(DatabaseCallback<T> paramDatabaseCallback, long paramLong);

    public abstract void queryFirst(DatabaseCallback<T> paramDatabaseCallback, String paramString, String[] paramArrayOfString);

    public abstract void update(DatabaseCallback<Boolean> paramDatabaseCallback, T paramT, String paramString, String[] paramArrayOfString);
}