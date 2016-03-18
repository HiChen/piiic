package com.gewara.piiic.utility;

/**
 * Created by user on 2016/1/26.
 */

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

public final class CacheUtil {
    public static File getAttachmentFile(Context paramContext, int paramInt, String paramString) {
        if (TextUtils.isEmpty(paramString))
            return null;
        return new File(getAttachmentsDir(paramContext, Integer.valueOf(paramInt)), paramString.substring(1 + paramString.lastIndexOf("/")));
    }

    public static File getAttachmentsDir(Context paramContext, Object paramObject) {
        File localFile = new File(getDir(paramContext, "attachments"), paramObject.toString());
        if (!localFile.exists())
            localFile.mkdirs();
        return localFile;
    }

    public static File getAvatarDir(Context paramContext) {
        return getDir(paramContext, "avatars");
    }

    public static File getAvatarFile(Context paramContext, String paramString) {
        if (TextUtils.isEmpty(paramString))
            return null;
        return new File(getAvatarDir(paramContext), paramString.substring(1 + paramString.lastIndexOf("/")));
    }

    private static File getDir(Context paramContext, String paramString) {
        File localFile = new File(getDiskCacheDir(paramContext), paramString);
        if (!localFile.exists())
            localFile.mkdirs();
        return localFile;
    }

    private static File getDiskCacheDir(Context paramContext) {
        boolean bool = Environment.getExternalStorageState().equals("mounted");
        File localFile = null;
        if (bool)
            localFile = getExternalCacheDir(paramContext);
        if (localFile == null)
            localFile = paramContext.getCacheDir();
        return localFile;
    }

    private static File getExternalCacheDir(Context paramContext) {
        File localFile1 = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File localFile2 = new File(new File(localFile1, paramContext.getPackageName()), "cache");
        if (!localFile2.exists()) ;
        try {
            new File(localFile1, ".nomedia").createNewFile();
            if (!localFile2.mkdirs()) {
                localFile2 = null;
            }
        } catch (IOException localIOException) {
        }
        return localFile2;
    }

    public static File getFontDir(Context paramContext) {
        return getDir(paramContext, "fonts");
    }

    public static File getFontFile(Context paramContext, String paramString) {
        if (TextUtils.isEmpty(paramString))
            return null;
        return new File(getFontDir(paramContext), paramString.substring(1 + paramString.lastIndexOf("/")));
    }

    public static File getFooterDir(Context paramContext) {
        return getDir(paramContext, "footers");
    }

    public static File getFooterFile(Context paramContext, String paramString) {
        if (TextUtils.isEmpty(paramString))
            return null;
        return new File(getFooterDir(paramContext), paramString.substring(1 + paramString.lastIndexOf("/")));
    }

    public static File getPaperFile(Context paramContext, String paramString) {
        if (TextUtils.isEmpty(paramString))
            return null;
        return new File(getPapersDir(paramContext), paramString.substring(1 + paramString.lastIndexOf("/")));
    }

    public static String getPaperPath(String paramString) {
        if (TextUtils.isEmpty(paramString))
            return paramString;
        return paramString.substring(1 + paramString.lastIndexOf("/"));
    }

    public static File getPapersDir(Context paramContext) {
        return getDir(paramContext, "papers");
    }

    public static File getShareDir(Context paramContext) {
        return getDir(paramContext, "shares");
    }

    public static File getShareFile(Context paramContext, String paramString) {
        if (TextUtils.isEmpty(paramString))
            return null;
        return new File(getShareDir(paramContext), paramString);
    }

    public static File getPiiicFile(Context paramContext, String paramString) {
        File localFile = new File(Environment.getExternalStorageDirectory(), "Piiic");
        if ((!localFile.exists()) || (localFile.isFile()))
            localFile.mkdirs();
        return new File(localFile, paramString);
    }
}
