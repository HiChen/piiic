package com.gewara.piiic.configs;


import  android.util.Log;

public final class L {
    private static final Log LOG = new R();

    public static void d(String paramString1, String paramString2, Object[] paramArrayOfObject) {
        LOG.d(paramString1, paramString2, paramArrayOfObject);
    }

    public static void d(String paramString1, Throwable paramThrowable, String paramString2, Object[] paramArrayOfObject) {
        LOG.d(paramString1, paramThrowable, paramString2, paramArrayOfObject);
    }

    public static void e(String paramString1, String paramString2, Object[] paramArrayOfObject) {
        LOG.e(paramString1, paramString2, paramArrayOfObject);
    }

    public static void e(String paramString1, Throwable paramThrowable, String paramString2, Object[] paramArrayOfObject) {
        LOG.e(paramString1, paramThrowable, paramString2, paramArrayOfObject);
    }

    static class D implements Log {
        public void d(String paramString1, String paramString2, Object[] paramArrayOfObject) {
            if (paramString2 != null) {
                if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
                    paramString2 = String.format(paramString2, paramArrayOfObject);
                android.util.Log.d(paramString1, paramString2);
            }
        }

        public void d(String paramString1, Throwable paramThrowable, String paramString2, Object[] paramArrayOfObject) {
            if (paramString2 != null) {
                if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
                    paramString2 = String.format(paramString2, paramArrayOfObject);
                android.util.Log.d(paramString1, paramString2, paramThrowable);
            }
        }

        public void e(String paramString1, String paramString2, Object[] paramArrayOfObject) {
            if (paramString2 != null) {
                if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
                    paramString2 = String.format(paramString2, paramArrayOfObject);
                android.util.Log.e(paramString1, paramString2);
            }
        }

        public void e(String paramString1, Throwable paramThrowable, String paramString2, Object[] paramArrayOfObject) {
            if (paramString2 != null) {
                if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
                    paramString2 = String.format(paramString2, paramArrayOfObject);
                android.util.Log.e(paramString1, paramString2, paramThrowable);
            }
        }
    }

    static abstract interface Log {
        public abstract void d(String paramString1, String paramString2, Object[] paramArrayOfObject);

        public abstract void d(String paramString1, Throwable paramThrowable, String paramString2, Object[] paramArrayOfObject);

        public abstract void e(String paramString1, String paramString2, Object[] paramArrayOfObject);

        public abstract void e(String paramString1, Throwable paramThrowable, String paramString2, Object[] paramArrayOfObject);
    }

    static class R implements Log {
        public void d(String paramString1, String paramString2, Object[] paramArrayOfObject) {
        }

        public void d(String paramString1, Throwable paramThrowable, String paramString2, Object[] paramArrayOfObject) {
        }

        public void e(String paramString1, String paramString2, Object[] paramArrayOfObject) {
        }

        public void e(String paramString1, Throwable paramThrowable, String paramString2, Object[] paramArrayOfObject) {
        }
    }
}