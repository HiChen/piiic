package com.gewara.piiic.task;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by user on 2016/1/26.
 */
public class BackgroundExecutorService {


    public static class FILE {
        public static <T> void execute(BackgroundTask fileDownloadTask) {
            new Task<T>(fileDownloadTask).execute((Void)null);
        }
    }
    public static class OTHER {
        public static <T> void execute(BackgroundTask<T> backgroundTask) {
            new Task<T>(backgroundTask).execute((Void)null);
        }
    }

    public static class SYNC {
        public static <T> void execute(BackgroundTask backgroundTask){
            new Task<T>(backgroundTask).execute((Void)null);
        }
    }

    public static class SVPIC{
        public static <T> void execute(BackgroundTask backgroundTask){
            new Task<T>(backgroundTask).execute((Void)null);
        }
    }

    static class Task<T> extends AsyncTask<Void, Void, T> {

        private BackgroundTask<T> mTask;

        public Task(BackgroundTask<T> t) {
            mTask = t;
        }

        @Override
        protected T doInBackground(Void... params) {
            return mTask.runTask();
        }

        @Override
        protected void onPreExecute() {
            mTask.preExecute();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(T t) {
            mTask.postExecute(t);
            super.onPostExecute(t);
        }
    }
}
