package com.gewara.piiic.utility;

/**
 * Created by user on 2016/1/26.
 */

import android.os.Environment;

import java.io.File;
import java.io.IOException;


public class FileUtils {
    private static String SDPATH = Environment.getExternalStorageDirectory() +"/Piiic/";
    public static File createFile(String paramString) {
        File localFile = new File(SDPATH +paramString);
        if (!localFile.getParentFile().exists())
            localFile.getParentFile().mkdirs();
        if (localFile.exists())
            localFile.delete();
        try {
            localFile.createNewFile();
            return localFile;
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return localFile;
    }

    public static File createFile(String paramString1, String paramString2) {
        File localFile1 = new File(paramString1);
        if (!localFile1.isDirectory())
            localFile1.delete();
        if (!localFile1.exists())
            localFile1.mkdirs();
        File localFile2 = new File(paramString1 + paramString2);
        if (localFile2.exists())
            localFile2.delete();
        try {
            localFile2.createNewFile();
            return localFile2;
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return localFile2;
    }


    public static File createSDFile(String paramString)
            throws IOException {
        File localFile = new File(SDPATH + paramString);
        if (localFile.exists())
            localFile.delete();
        localFile.createNewFile();
        return localFile;
    }

    public static String getSDPATH() {
        return SDPATH;
    }

    //清空文件夹的子文件
    public static void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
//                    try {
//                        f.delete();
//                    } catch (Exception e) {
//                    }
                } else {
                    if (f.exists() && f.getName().contains("temp")) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
    }

}
