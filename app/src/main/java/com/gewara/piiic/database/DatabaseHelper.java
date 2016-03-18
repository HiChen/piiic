package com.gewara.piiic.database;

/**
 * Created by user on 2016/1/26.
 */
import java.util.Collection;
import java.util.List;

public abstract interface DatabaseHelper<T>
{
    public abstract long delete(String paramString, String[] paramArrayOfString);

    public abstract boolean delete(T paramT);

    public abstract long getCount(String paramString, String[] paramArrayOfString);

    public abstract long insert(T paramT);

    public abstract boolean insertBatch(Collection<T> paramCollection);

    public abstract T insertOrUpdate(T paramT, String paramString, String[] paramArrayOfString);

    public abstract boolean isClosed();

    public abstract List<T> query(String paramString, String[] paramArrayOfString);

    public abstract T queryById(long paramLong);

    public abstract T queryFirst(String paramString, String[] paramArrayOfString);

    public abstract boolean update(T paramT, String paramString, String[] paramArrayOfString);
}
