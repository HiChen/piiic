package com.gewara.piiic.database;


import com.gewara.piiic.database.async.AsyncDatabaseHelper;

/**
 * Created by user on 2016/1/26.
 */
public class PiiicDB {

    public static DatabaseHelper ARTICLE() {
        return null;
    }


    public static AsyncDatabaseHelper A_PAPER;
    public static AsyncDatabaseHelper A_ATTACHMENT;
    public static DatabaseHelper ATTACHMENT;

    public static DatabaseHelper ARTICLE;

    public static AsyncDatabaseHelper A_ARTICLE;

    public static DatabaseHelper COLOR;

    public static DatabaseHelper FONT;

    public static DatabaseHelper PAPER;
}