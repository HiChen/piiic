package com.gewara.piiic.task;

/**
 * Created by user on 2016/1/29.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;

import com.gewara.piiic.Configs;
import com.gewara.piiic.R;
import com.gewara.piiic.configs.L;
import com.gewara.piiic.models.Article;
import com.gewara.piiic.utility.BitmapUtils;
import com.gewara.piiic.utility.CacheUtil;
import com.gewara.piiic.utility.FileUtils;

import java.io.File;

public class SaveImageTask extends BackgroundTask<Bitmap> {
    private static final String TAG = Configs.makeTag("SaveImageTask");
    protected final Article mArticle;
    protected final Context mContext;
    protected final boolean mSaveExternal;
    protected final float mScale;
    protected final boolean mSquareImage;
    protected final WebView mWebView;
//    private AccountPreferences mAccountPreferences;
    private int height = 0;

    public SaveImageTask(Context paramContext, WebView paramWebView, Article paramArticle) {
        this(paramContext, paramWebView, paramArticle, false, false);
    }

//    public  SaveImageTask(Context paramContext, WebView paramWebView, Article paramArticle, boolean paramBoolean) {
//        this(paramContext, paramWebView, paramArticle, false, paramBoolean);
//    }

    public SaveImageTask(Context paramContext, WebView paramWebView, Article paramArticle, boolean paramBoolean1, boolean paramBoolean2) {
        this.mContext = paramContext;
        this.mWebView = paramWebView;
        this.mScale = paramWebView.getScale();
        this.mArticle = paramArticle;
        this.mSquareImage = paramBoolean1;
        this.mSaveExternal = paramBoolean2;

//        mAccountPreferences = AccountPreferences.getInstance(paramContext);
        height = (int)(mWebView.getContentHeight() * mScale);
    }

//    private static Bitmap createAvatarFooterBitmap(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
//        View localView = LayoutInflater.from(paramContext).inflate(paramInt2, null);
//        localView.setBackgroundColor(0);
//        setFooterContent(paramContext, localView, paramInt3, paramInt4);
//        return createFooterBitmap(paramContext, paramInt1, localView);
//    }

    private BitmapDrawable createBackground(File paramFile) {
        Bitmap localBitmap1 = BitmapFactory.decodeFile(paramFile.getAbsolutePath());
        Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, (int) (localBitmap1.getWidth() * this.mScale), (int) (localBitmap1.getHeight() * this.mScale), true);
        recycleBitmap(localBitmap1);
        BitmapDrawable localBitmapDrawable = new BitmapDrawable(this.mContext.getResources(), localBitmap2);
        localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        return localBitmapDrawable;
    }

//    private static Bitmap createCustomizeFooterBitmap(Context paramContext, int paramInt, Footer paramFooter) {
//        View localView = LayoutInflater.from(paramContext).inflate(R.layout.footer_customize_view, null);
//        localView.setBackgroundColor(0);
//        ImageView localImageView = (ImageView) localView.findViewById(R.id.activity_edit_footer_customize_footer);
//        String str = paramFooter.getImage();
//        if (!TextUtils.isEmpty(str)) {
//            File localFile = CacheUtil.getFooterFile(paramContext, str);
//            if ((localFile != null) && (localFile.exists()) && (localFile.isFile())) {
//                localImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                localImageView.setImageURI(Uri.fromFile(localFile));
//            }
//        }
//        return createFooterBitmap(paramContext, paramInt, localView);
//    }

//    private static Bitmap createFooterBitmap(Context paramContext, int paramInt, View paramView) {
//        int i = View.MeasureSpec.makeMeasureSpec(paramInt, MeasureSpec.EXACTLY);
//        int j = paramContext.getResources().getDimensionPixelSize(R.dimen.footer_height);
//        paramView.measure(i, View.MeasureSpec.makeMeasureSpec(j, MeasureSpec.EXACTLY));
//        paramView.layout(0, 0, paramView.getMeasuredWidth(), paramView.getMeasuredHeight());
//        Bitmap localBitmap = Bitmap.createBitmap(paramInt, j, Bitmap.Config.ARGB_8888);
//        paramView.draw(new Canvas(localBitmap));
//        return localBitmap;
//    }

    private void drawArticle(Canvas paramCanvas, int paramInt1, int paramInt2) {
        int i = paramCanvas.save();
        paramCanvas.clipRect(0, 0, paramInt1, paramInt2);
        this.mWebView.draw(paramCanvas);
        paramCanvas.restoreToCount(i);
    }

    private boolean drawPaper(Canvas paramCanvas) {
//        Paper localPaper = getPaper();
        boolean bool = false;
        String str = null;
        /*if ((localPaper != null) && (!TextUtils.isEmpty(localPaper.getDetail()))) {
            bool = true;
            str = localPaper.getDetail();
            if (!localPaper.isColor())
                paramCanvas.drawColor(Color.parseColor(str));
        }*/
        File localFile = CacheUtil.getPaperFile(this.mContext, str);
        if ((localFile == null) || (!localFile.isFile()) || (!localFile.exists())) {
            bool = false;
            return bool;
        }

        BitmapDrawable localBitmapDrawable = createBackground(localFile);
        localBitmapDrawable.setBounds(0, 0, paramCanvas.getWidth(), paramCanvas.getHeight());
        localBitmapDrawable.draw(paramCanvas);
        paramCanvas.drawColor(Color.WHITE);
        return bool;
    }

    private static int getBytesPerPixel(Bitmap.Config paramConfig) {
        int i = 2;
        if (paramConfig == Bitmap.Config.ARGB_8888)
            i = 4;
        while ((paramConfig == Bitmap.Config.RGB_565) || (paramConfig == Bitmap.Config.ARGB_4444))
            return i;
        if (paramConfig == Bitmap.Config.ALPHA_8)
            return 1;
        return 1;
    }

    private static Bitmap getFooterBitmap(Context paramContext, int paramInt) {
            return BitmapFactory.decodeResource(paramContext.getResources(), R.drawable.image_polaroid);
    }

//    private static String getImageDate(Article paramArticle) {
//        Date localDate = paramArticle.getClientModified();
//        if (localDate != null)
//            return DateUtil.getSimpleString(localDate);
//        return DateUtil.getSimpleString(new Date());
//    }
//
//    public static File getImageFile(Context paramContext, Article paramArticle, boolean paramBoolean) {
//        File localObject;
//        try {
//            Footer localFooter = AccountPreferences.getFooter();
//            Object[] arrayOfObject = new Object[3];
//            arrayOfObject[0] = paramArticle.getId();
//            arrayOfObject[1] = getImageDate(paramArticle);
//            arrayOfObject[2] = Integer.valueOf(localFooter.getSelected());
//            String str = String.format("%d-%s-%d.jpg", arrayOfObject);
//            if (paramBoolean) ;
//            File localFile = CacheUtil.getShareFile(paramContext, str);
//            for (localObject = CacheUtil.getPiiicFile(paramContext, str); (!((File) localObject).exists()) || (((File) localObject).isDirectory()); localObject = localFile) {
//                ((File) localObject).createNewFile();
//                return localObject;
//            }
//        } catch (Exception localException) {
//            L.e(TAG, localException, localException.getMessage(), new Object[0]);
//            localObject = null;
//        }
//        return localObject;
//    }

    private static int getMaxMemory() {
        return (int) Runtime.getRuntime().maxMemory();
    }

//    private Paper getPaper() {
//        String str1 = this.mArticle.getStyle();
//        if ((!TextUtils.isEmpty(str1)) && (!"default".equals(str1))) {
//            DatabaseHelper localDatabaseHelper = PiiicDB.PAPER;
//            String str2 = String.format("%s=? and %s=?", new Object[]{"_owner_email", "_name"});
//            String[] arrayOfString = new String[2];
//            arrayOfString[0] = mAccountPreferences.getEmail();
//            arrayOfString[1] = str1;
//            return (Paper) localDatabaseHelper.queryFirst(str2, arrayOfString);
//        }
//        return null;
//    }

    protected static void recycleBitmap(Bitmap paramBitmap) {
        paramBitmap.recycle();
    }

    // ERROR //
    private static boolean saveBitmap(Bitmap paramBitmap) {
        if (paramBitmap!= null) {
            File localFile = FileUtils.createFile("changweibo.jpg");
            FileUtils.deleteAllFiles(new File(FileUtils.getSDPATH()));
            BitmapUtils.saveBitmap(paramBitmap, localFile);
            paramBitmap.recycle();
            return  true;
        }else{
           return  false;
        }
    }

//    private static void setFooterContent(Context paramContext, View paramView, int paramInt1, int paramInt2) {
//        Resources localResources = paramContext.getResources();
//        Account localAccount = AccountPreferences.getAccount();
//        String str = localAccount.getAvatar();
//        if (!TextUtils.isEmpty(str)) {
//            File localFile = CacheUtil.getAvatarFile(paramContext, str);
//            if ((localFile != null) && (localFile.exists()) && (localFile.isFile()))
//                ((ImageView) paramView.findViewById(paramInt1)).setImageURI(Uri.fromFile(localFile));
//        }
//        SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(localAccount.getUsername());
//        localSpannableStringBuilder.append("\n");
//        AbsoluteSizeSpan localAbsoluteSizeSpan = new AbsoluteSizeSpan(localResources.getDimensionPixelSize(R.dimen.large_text_size));
//        ForegroundColorSpan localForegroundColorSpan = new ForegroundColorSpan(localResources.getColor(R.color.black));
//        int i = localSpannableStringBuilder.length();
//        localSpannableStringBuilder.setSpan(localAbsoluteSizeSpan, 0, i, 33);
//        localSpannableStringBuilder.setSpan(localForegroundColorSpan, 0, i, 33);
//        localSpannableStringBuilder.append(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
//        ((TextView) paramView.findViewById(paramInt2)).setText(localSpannableStringBuilder);
//    }

    public void postExecute(Bitmap paramBitmap) {
        if (paramBitmap != null)
            recycleBitmap(paramBitmap);
    }

    public Bitmap runTask() {
        int i = this.mWebView.getWidth();
        int j = height;
        int m = 0;
        int n = 0;
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(localDisplayMetrics);
        int k = localDisplayMetrics.heightPixels;
        String str1 = TAG;
        Object[] arrayOfObject1 = new Object[3];
        arrayOfObject1[0] = Integer.valueOf(i);
        arrayOfObject1[1] = Integer.valueOf(j);
        arrayOfObject1[2] = Integer.valueOf(k);
        L.d(str1, "Webview width: %d, height: %d, displahHeight: %d", arrayOfObject1);
        try {
            if (this.mSquareImage) {
                Bitmap localBitmap3 = Bitmap.createBitmap(i, i, Bitmap.Config.RGB_565);
                Canvas localCanvas2 = new Canvas(localBitmap3);
                drawPaper(localCanvas2);
                drawArticle(localCanvas2, i, i);
                return localBitmap3;
            }
            Bitmap localBitmap1 = getFooterBitmap(this.mContext, i);
            if ((localBitmap1 != null) && (localBitmap1.getWidth() > 0) && (localBitmap1.getHeight() > 0)) {
                m = 1;
                if (m == 0)
                    n = localBitmap1.getHeight();
                int i1 = 2 * getMaxMemory() / 3 / getBytesPerPixel(Bitmap.Config.RGB_565) / i;
                int i2 = Math.min(Math.max(j + n, i), i1);
                String str2 = TAG;
                Object[] arrayOfObject2 = new Object[2];
                arrayOfObject2[0] = Integer.valueOf(i1);
                arrayOfObject2[1] = Integer.valueOf(i2);
                L.d(str2, "Webview maxHeight: %d, bitmapHeight: %d", arrayOfObject2);

                Bitmap localBitmap2 = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
                Canvas localCanvas1 = new Canvas(localBitmap2);
//                drawPaper(localCanvas1);
                drawArticle(localCanvas1, i, Math.min(j, i2 - n));
                if (m != 0) {
                    localCanvas1.drawBitmap(localBitmap1, 0.0F, i2 - n, null);
                    recycleBitmap(localBitmap1);
                }
                saveBitmap(localBitmap2);
                recycleBitmap(localBitmap2);
                return localBitmap1;
            }
        } catch (OutOfMemoryError localOutOfMemoryError) {
            L.e(TAG, localOutOfMemoryError, localOutOfMemoryError.getMessage(), new Object[0]);
        } catch (Exception localException) {
            L.e(TAG, localException, localException.getMessage(), new Object[0]);
        }finally {
            System.gc();
        }
        return null;
    }
}
