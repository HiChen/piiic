package com.gewara.piiic.database.async;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.gewara.piiic.database.DatabaseHelper;
import com.gewara.piiic.models.BaseModel;
import com.gewara.piiic.task.BackgroundExecutor;
import com.gewara.piiic.task.BackgroundTask;

public class PiiicAsyncDatabaseHelper<T extends BaseModel>
        implements AsyncDatabaseHelper<T> {
    private final DatabaseHelper<T> mDatabaseHelper;
    private final BackgroundExecutor mExecutor;

    public PiiicAsyncDatabaseHelper(BackgroundExecutor paramBackgroundExecutor, DatabaseHelper<T> paramDatabaseHelper) {
        this.mExecutor = paramBackgroundExecutor;
        this.mDatabaseHelper = paramDatabaseHelper;
    }

    public void delete(final DatabaseCallback<Long> paramDatabaseCallback, final String paramString, final String[] paramArrayOfString) {
        this.mExecutor.execute(new DatabaseTask(/*paramString, paramArrayOfString*/) {
            public void postExecute(Long paramAnonymousLong) {
                DatabaseCallback localDatabaseCallback;
                if (paramDatabaseCallback != null) {
                    localDatabaseCallback = paramDatabaseCallback;
                    if (paramAnonymousLong == null)
                        paramAnonymousLong = Long.valueOf(0L);
//                    localDatabaseCallback.onCompleted(paramAnonymousLong);
                }
            }

            Long runDatabaseTask(DatabaseHelper paramAnonymousDatabaseHelper) {
                return Long.valueOf(paramAnonymousDatabaseHelper.delete(paramString, paramArrayOfString));
            }
        });
    }

    public void delete(final T paramT, final DatabaseCallback<Boolean> paramDatabaseCallback) {
        this.mExecutor.execute(new DatabaseTask(/*paramT, paramDatabaseCallback*/) {
            public void postExecute(Boolean paramAnonymousBoolean) {
                DatabaseCallback localDatabaseCallback;
                if (paramDatabaseCallback != null) {
                    localDatabaseCallback = paramDatabaseCallback;
                    if (paramAnonymousBoolean == null)
                        return;
//                    localDatabaseCallback.onCompleted(paramAnonymousBoolean);
                    paramAnonymousBoolean = Boolean.FALSE;
                }
            }

            Boolean runDatabaseTask(DatabaseHelper paramAnonymousDatabaseHelper) {
                return Boolean.valueOf(paramAnonymousDatabaseHelper.delete(paramT));
            }
        });
    }

    public void getCount(final DatabaseCallback<Long> paramDatabaseCallback, final String paramString, final String[] paramArrayOfString) {
        this.mExecutor.execute(new DatabaseTask(/*paramString, paramArrayOfString*/) {
            public void postExecute(Long paramAnonymousLong) {
                DatabaseCallback localDatabaseCallback;
                if (paramDatabaseCallback != null) {
                    localDatabaseCallback = paramDatabaseCallback;
                    if (paramAnonymousLong == null)
                        return;
//                    localDatabaseCallback.onCompleted(paramAnonymousLong);
                    paramAnonymousLong = Long.valueOf(0L);
                }
            }

            Long runDatabaseTask(DatabaseHelper paramAnonymousDatabaseHelper) {
                return Long.valueOf(paramAnonymousDatabaseHelper.getCount(paramString, paramArrayOfString));
            }
        });
    }

    public void insert(final T paramT, final DatabaseCallback<Long> paramDatabaseCallback) {
        this.mExecutor.execute(new DatabaseTask(/*paramT, paramDatabaseCallback*/) {
            public void postExecute(Long paramAnonymousLong) {
//                if (paramDatabaseCallback != null)
//                    paramDatabaseCallback.onCompleted(paramAnonymousLong);
            }

            Long runDatabaseTask(DatabaseHelper paramAnonymousDatabaseHelper) {
                return Long.valueOf(paramAnonymousDatabaseHelper.insert(paramT));
            }
        });
    }

    public void insertBatch(final Collection<T> paramCollection, final DatabaseCallback<Boolean> paramDatabaseCallback) {
        this.mExecutor.execute(new DatabaseTask(/*paramCollection, paramDatabaseCallback*/) {
            public void postExecute(Boolean paramAnonymousBoolean) {
                DatabaseCallback localDatabaseCallback;
                if (paramDatabaseCallback != null) {
                    localDatabaseCallback = paramDatabaseCallback;
                    if (paramAnonymousBoolean == null) {
                        return;
                    }
//                    localDatabaseCallback.onCompleted(paramAnonymousBoolean);
                    paramAnonymousBoolean = Boolean.FALSE;
                }
            }

                Boolean runDatabaseTask(DatabaseHelper paramAnonymousDatabaseHelper) {
                return Boolean.valueOf(paramAnonymousDatabaseHelper.insertBatch(paramCollection));
            }
        });
    }

    public void insertOrUpdate(final DatabaseCallback<T> paramDatabaseCallback, final T paramT, final String paramString, final String[] paramArrayOfString) {
        this.mExecutor.execute(new DatabaseTask(/*paramT, paramString*/) {
            public void postExecute(T paramAnonymousT) {
//                if (paramDatabaseCallback != null)
//                    paramDatabaseCallback.onCompleted(paramAnonymousT);
            }

            T runDatabaseTask(DatabaseHelper paramAnonymousDatabaseHelper) {
                return (T) paramAnonymousDatabaseHelper.insertOrUpdate(paramT, paramString, paramArrayOfString);
            }
        });
    }

    public void query(final DatabaseCallback<List<T>> paramDatabaseCallback, final String paramString, final String[] paramArrayOfString) {
        this.mExecutor.execute(new DatabaseTask(/*paramString, paramArrayOfString*/) {
            public void postExecute(List<T> paramAnonymousList) {
                DatabaseCallback localDatabaseCallback;
                if (paramDatabaseCallback != null) {
                    localDatabaseCallback = paramDatabaseCallback;
                    if (paramAnonymousList == null)
                        return;
                    localDatabaseCallback.onCompleted(paramAnonymousList);

                    paramAnonymousList = Collections.EMPTY_LIST;
                }
            }

            List<T> runDatabaseTask(DatabaseHelper paramAnonymousDatabaseHelper) {
                return PiiicAsyncDatabaseHelper.this.mDatabaseHelper.query(paramString, paramArrayOfString);
            }
        });
    }

    public void queryById(final DatabaseCallback<T> paramDatabaseCallback, final long paramLong) {
        this.mExecutor.execute(new DatabaseTask(/*paramLong, paramDatabaseCallback*/) {
            public void postExecute(T paramAnonymousT) {
//                if (paramDatabaseCallback != null)
//                    paramDatabaseCallback.onCompleted(paramAnonymousT);
            }

            T runDatabaseTask(DatabaseHelper paramAnonymousDatabaseHelper) {
                return (T) PiiicAsyncDatabaseHelper.this.mDatabaseHelper.queryById(paramLong);
            }
        });
    }

    public void queryFirst(final DatabaseCallback<T> paramDatabaseCallback, final String paramString, final String[] paramArrayOfString) {
        this.mExecutor.execute(new DatabaseTask(/*paramString, paramArrayOfString*/) {
            public void postExecute(T paramAnonymousT) {
//                if (paramDatabaseCallback != null)
//                    paramDatabaseCallback.onCompleted(paramAnonymousT);
            }

            T runDatabaseTask(DatabaseHelper paramAnonymousDatabaseHelper) {
                return (T) PiiicAsyncDatabaseHelper.this.mDatabaseHelper.queryFirst(paramString, paramArrayOfString);
            }
        });
    }

    public void update(final DatabaseCallback<Boolean> paramDatabaseCallback, final T paramT, final String paramString, final String[] paramArrayOfString) {
        this.mExecutor.execute(new DatabaseTask(/*paramT, paramString*/) {
            public void postExecute(Boolean paramAnonymousBoolean) {
                DatabaseCallback localDatabaseCallback;
//                if (paramDatabaseCallback != null) {
//                    localDatabaseCallback = paramDatabaseCallback;
//                    if (paramAnonymousBoolean == null)
//                        return;
//                    localDatabaseCallback.onCompleted(paramAnonymousBoolean);
//                    paramAnonymousBoolean = Boolean.FALSE;
//                }
            }

            Boolean runDatabaseTask(DatabaseHelper paramAnonymousDatabaseHelper) {
                return Boolean.valueOf(paramAnonymousDatabaseHelper.update(paramT, paramString, paramArrayOfString));
            }
        });
    }

    private abstract class DatabaseTask<R> extends BackgroundTask<R> {
        private DatabaseTask() {
        }

        abstract R runDatabaseTask(DatabaseHelper paramDatabaseHelper);

        public final R runTask() {
            DatabaseHelper localDatabaseHelper = PiiicAsyncDatabaseHelper.this.mDatabaseHelper;
            if (localDatabaseHelper.isClosed())
                return null;
            return runDatabaseTask(localDatabaseHelper);
        }
    }
}