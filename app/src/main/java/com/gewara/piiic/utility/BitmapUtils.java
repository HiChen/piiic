package com.gewara.piiic.utility;

/**
 * Created by user on 2016/1/26.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.gewara.piiic.PiiicApplication;
import com.gewara.piiic.configs.L;

public class BitmapUtils {
    public static Bitmap createReflectionImageWithOrigin(Bitmap paramBitmap) {
        int i = paramBitmap.getWidth();
        int j = paramBitmap.getHeight();
        Matrix localMatrix = new Matrix();
        localMatrix.preScale(1.0F, -1.0F);
        Bitmap localBitmap1 = Bitmap.createBitmap(paramBitmap, 0, j / 2, i, j / 2, localMatrix, false);
        Bitmap localBitmap2 = Bitmap.createBitmap(i, j + j / 2, Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap2);
        localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, null);
        Paint localPaint1 = new Paint();
        localCanvas.drawRect(0.0F, j, i, j + 4, localPaint1);
        localCanvas.drawBitmap(localBitmap1, 0.0F, j + 4, null);
        Paint localPaint2 = new Paint();
        localPaint2.setShader(new LinearGradient(0.0F, paramBitmap.getHeight(), 0.0F, 4 + localBitmap2.getHeight(), 1895825407, 16777215, TileMode.CLAMP));
        localPaint2.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        localCanvas.drawRect(0.0F, j, i, 4 + localBitmap2.getHeight(), localPaint2);
        return localBitmap2;
    }

    public static Bitmap createViewBitmap(View paramView) {
        return createViewBitmap(paramView, 200, 200);
    }

    public static Bitmap createViewBitmap(View paramView, int paramInt1, int paramInt2) {
        Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        int i = localCanvas.save();
        localCanvas.clipRect(0, 0, paramInt1, paramInt2);
        paramView.draw(localCanvas);
        localCanvas.restoreToCount(i);
        return localBitmap;
    }

    public static Bitmap drawableToBitmap(Drawable paramDrawable) {
        int i = paramDrawable.getIntrinsicWidth();
        int j = paramDrawable.getIntrinsicHeight();
        if (paramDrawable.getOpacity() != -1) ;
        for (Config localConfig = Config.ARGB_8888; ; localConfig = Config.RGB_565) {
            Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
            Canvas localCanvas = new Canvas(localBitmap);
            paramDrawable.setBounds(0, 0, i, j);
            paramDrawable.draw(localCanvas);
            return localBitmap;
        }
    }

    public static String getAbsoluteImagePath(Activity paramActivity, Uri paramUri) {
        Cursor localCursor = paramActivity.managedQuery(paramUri, new String[]{"_data"}, null, null, null);
        int i = localCursor.getColumnIndexOrThrow("_data");
        localCursor.moveToFirst();
        return localCursor.getString(i);
    }

    public static Bitmap getBitmap(Context paramContext, int paramInt) {
        return BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
    }

    private static Options getBitmapOption(int paramInt) {
        System.gc();
        Options localOptions = new Options();
        localOptions.inPurgeable = true;
        localOptions.inSampleSize = paramInt;
        return localOptions;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap paramBitmap, float paramFloat) {
        int i = paramBitmap.getWidth();
        int j = paramBitmap.getHeight();
        Bitmap localBitmap = Bitmap.createBitmap(i, j, Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        Paint localPaint = new Paint();
        Rect localRect = new Rect(0, 0, i, j);
        RectF localRectF = new RectF(localRect);
        localPaint.setAntiAlias(true);
        localCanvas.drawARGB(0, 0, 0, 0);
        localPaint.setColor(Color.GRAY);
        localCanvas.drawRoundRect(localRectF, paramFloat, paramFloat, localPaint);
        localPaint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
        return localBitmap;
    }

    public static boolean initImageView(ImageView paramImageView, File paramFile) {
        if ((paramFile != null) && (paramFile.exists()) && (paramFile.length() > 1024L)) {
            paramImageView.setImageURI(Uri.fromFile(paramFile));
            return true;
        }
        return false;
    }

    private static void initPicIntent(Intent paramIntent, int paramInt1, int paramInt2) {
        paramIntent.putExtra("crop", "true");
        paramIntent.putExtra("outputX", paramInt1);
        paramIntent.putExtra("outputY", paramInt2);
        paramIntent.putExtra("return-data", true);
    }

    public static void pickPicture(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, File paramFile) {
        try {
            Intent localIntent1 = new Intent("android.intent.action.PICK", null);
            localIntent1.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
            initPicIntent(localIntent1, paramInt2, paramInt3);
            if (paramFile != null) {
                localIntent1.putExtra("output", Uri.fromFile(paramFile));
                localIntent1.putExtra("outputFormat", "PNG");
            }
            paramActivity.startActivityForResult(localIntent1, paramInt1);
            return;
        } catch (Exception localException1) {
            try {
                Intent localIntent2 = new Intent();
                localIntent2.setType("image/*");
                localIntent2.setAction("android.intent.action.GET_CONTENT");
                initPicIntent(localIntent2, paramInt2, paramInt3);
                paramActivity.startActivityForResult(localIntent2, paramInt1);
                return;
            } catch (Exception localException2) {
            }
        }
    }

    public static Bitmap rotateBitmap(Bitmap paramBitmap) {
        Matrix localMatrix = new Matrix();
        localMatrix.setRotate(90.0F);
        int i = paramBitmap.getWidth();
        int j = paramBitmap.getHeight();
        try {
            Bitmap localBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
            return localBitmap;
        } catch (OutOfMemoryError localOutOfMemoryError) {
            localMatrix.postScale(0.5F, 0.5F);
        }
        return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
    }

    public static boolean saveBitmap(Bitmap paramBitmap) {
        Date localDate = new Date();
        try {
            File localFile = FileUtils.createSDFile("Piiic" + localDate.getTime() + ".png");
            if (localFile.exists())
                localFile.delete();
            saveBitmap(paramBitmap, localFile);
            scanFileAsync(localFile.getAbsolutePath());
            return true;
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return false;
    }

    public static File saveBitmapToFile(Bitmap paramBitmap) {
        Date localDate = new Date();
        try {
            File localFile = FileUtils.createSDFile("Piiic" + localDate.getTime() + ".png");
//            if (localFile.exists())
//                localFile.delete();
            saveBitmap(paramBitmap, localFile);
            scanFileAsync(localFile.getAbsolutePath());
            return localFile;
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return null;
    }

    public static Uri saveBitmapToUri(Bitmap paramBitmap) {
        Date localDate = new Date();
        try {
            File localFile = FileUtils.createFile("temp" + localDate.getTime() + ".png");
            saveBitmap(paramBitmap, localFile);
            return Uri.parse(localFile.getAbsolutePath());
        } catch (Exception localIOException) {
            localIOException.printStackTrace();
        }finally {
            if (paramBitmap!= null){
                paramBitmap.recycle();
            }
        }
        return null;
    }

    public static boolean saveBitmap(Bitmap paramBitmap, File paramFile) {
        try {
            FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
            paramBitmap.compress(CompressFormat.JPEG, 50, localFileOutputStream);
            localFileOutputStream.flush();
            localFileOutputStream.close();
            paramBitmap.recycle();
            return true;
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return false;
    }



    public static boolean saveBitmap(View paramView) {
        paramView.setDrawingCacheEnabled(true);
        Bitmap localBitmap = Bitmap.createBitmap(paramView.getDrawingCache());
        paramView.setDrawingCacheEnabled(false);
        return saveBitmap(localBitmap);
    }

    public static String saveView(View paramView) {
        Bitmap localBitmap = createViewBitmap(paramView);
        File localFile = null;
        try {
            localFile = FileUtils.createSDFile("footer_icon.png");
            if (localFile.exists())
                localFile.delete();
            saveBitmap(localBitmap, localFile);
            return localFile.getAbsolutePath();
        } catch (IOException localIOException) {
            while (true)
                localIOException.printStackTrace();
        }
    }

    public static boolean saveView(Context paramContext, int paramInt, File paramFile) {
        return saveBitmap(getBitmap(paramContext, paramInt), paramFile);
    }

    public static boolean saveView(View paramView, File paramFile) {
        return saveBitmap(createViewBitmap(paramView), paramFile);
    }

    public static void scanFileAsync(String paramString) {
        Intent localIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        localIntent.setData(Uri.fromFile(new File(paramString)));
        PiiicApplication.getApplication().sendBroadcast(localIntent);
    }

    public static Bitmap zoomBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2) {
        int i = paramBitmap.getWidth();
        int j = paramBitmap.getHeight();
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(paramInt1 / i, paramInt2 / j);
        return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
    }

    public static Drawable zoomDrawable(Drawable paramDrawable, int paramInt1, int paramInt2) {
        int i = paramDrawable.getIntrinsicWidth();
        int j = paramDrawable.getIntrinsicHeight();
        Bitmap localBitmap = drawableToBitmap(paramDrawable);
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(paramInt1 / i, paramInt2 / j);
        return new BitmapDrawable(Bitmap.createBitmap(localBitmap, 0, 0, i, j, localMatrix, true));
    }

    public byte[] Bitmap2Bytes(Bitmap paramBitmap) {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        paramBitmap.compress(CompressFormat.PNG, 100, localByteArrayOutputStream);
        return localByteArrayOutputStream.toByteArray();
    }

    public Drawable Bitmap2Drawable(Resources paramResources, Bitmap paramBitmap) {
        return new BitmapDrawable(paramResources, paramBitmap);
    }

    public Bitmap Bytes2Bimap(byte[] paramArrayOfByte) {
        if (paramArrayOfByte.length != 0)
            return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
        return null;
    }

    public static Bitmap file2Bitmap(String paramString) {
        return BitmapFactory.decodeFile(paramString, getBitmapOption(2));
    }
    public static Bitmap file2Bitmap(String paramString,int paramInt) {
        return BitmapFactory.decodeFile(paramString, getBitmapOption(paramInt));
    }
    public Bitmap res2Bitmap(Resources paramResources, int paramInt) {
        return BitmapFactory.decodeResource(paramResources, paramInt);
    }
    public static Bitmap compBitMap(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    private static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static BitmapFactory.Options resolveImageFromUri(Context paramContext, Uri paramUri) {
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        if (paramUri == null) ;
        InputStream localInputStream;

        String str = paramUri.getScheme();
        if (("content".equals(str)) || ("file".equals(str))) {
            localInputStream = null;
            try {
                localInputStream = paramContext.getContentResolver().openInputStream(paramUri);
                BitmapFactory.decodeStream(localInputStream, null, localOptions);
                if (localInputStream != null)
                    try {
                        localInputStream.close();
                        return localOptions;
                    } catch (IOException localIOException3) {
                        return localOptions;
                    }
            } catch (Exception localException) {
                if (localInputStream != null)
                    try {
                        localInputStream.close();
                        return localOptions;
                    } catch (IOException localIOException2) {
                        return localOptions;
                    }
            } finally {
                try {
                    if (localInputStream == null) ;
                    localInputStream.close();
                } catch (IOException localIOException1) {
                }
            }
        }
        return localOptions;
    }


    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight	|| (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}
