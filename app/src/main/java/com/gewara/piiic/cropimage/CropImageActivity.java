package com.gewara.piiic.cropimage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;

import com.gewara.piiic.Configs;
import com.gewara.piiic.R;
import com.gewara.piiic.activity.BaseActivity;
import com.gewara.piiic.utility.BitmapUtils;

/**
 * Created by user on 2016/1/28.
 */
public class CropImageActivity extends BaseActivity {
    private static final String TAG = Configs.makeTag("CropImageActivity");
    private CropImageView mCropImageView;
    private TextView mBtnSave, mBtnCancle;
    private Uri mUri;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        mUri = ((Uri) getIntent().getParcelableExtra("CropImageActivity.ImageUri"));
        initView();
    }

    private void initView() {
        mCropImageView = (CropImageView) findViewById(R.id.crop_image_view);
        mBtnSave = (TextView) findViewById(R.id.save_pic);
        mBtnCancle = (TextView) findViewById(R.id.canlce);
        runOnUiThread(new ShowImageRunnable(mUri));
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用该方法得到剪裁好的图片
                Bitmap mBitmap = mCropImageView.getCropImage();
                if (mBitmap != null){
                    Uri uri = BitmapUtils.saveBitmapToUri(mBitmap);
                    CropResult crop = new CropResult(uri, mBitmap.getWidth(), mBitmap.getHeight());
                    Intent localIntent = new Intent();
                    localIntent.putExtra("CropResult", crop);
                    localIntent.putExtra("CropImageActivity.Style", "imgCommonStyle");
                    setResult(2, localIntent);
                    mBitmap.recycle();
                }
                System.gc();
                finish();
            }
        });
        mBtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public static void startForResult(Activity paramActivity, Uri paramUri, int paramInt) {
        Intent localIntent = new Intent(paramActivity, CropImageActivity.class);
        localIntent.putExtra("CropImageActivity.ImageUri", paramUri);
        paramActivity.startActivityForResult(localIntent, paramInt);
    }



    @Override
    protected void onStop() {
        super.onStop();
        if (bmp != null) {
            bmp.recycle();
            bmp = null;
        }
        mCropImageView.mDrawable = null;
        mCropImageView.mDrawableDst = null;
        mCropImageView.mDrawableFloat = null;
        mCropImageView.mDrawableSrc = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (bmp != null) {
            bmp.recycle();
        }
        System.gc();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bmp != null) {
            bmp.recycle();
            bmp = null;
            System.gc();
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.crop_image_view;
    }
    private final class ShowImageRunnable implements Runnable {
        private final Uri mUrl;
        private ShowImageRunnable(Uri url) {
            mUrl = url;
        }

        public void run() {
            BitmapFactory.Options localOptions = BitmapUtils.resolveImageFromUri(CropImageActivity.this, mUrl);
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int mScreenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
            int mScreenHeight = dm.heightPixels;
            localOptions.inSampleSize = BitmapUtils.calculateInSampleSize(localOptions, mScreenWidth, mScreenHeight/2);
            localOptions.inJustDecodeBounds = false;
            try {
                bmp = BitmapFactory.decodeStream(CropImageActivity.this.getContentResolver().openInputStream(mUri), null, localOptions);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Drawable drawable = new BitmapDrawable(bmp);
            mCropImageView.setDrawable(drawable, bmp.getWidth(), bmp.getHeight());
            System.gc();
        }
    }
}
