package com.gewara.piiic.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.gewara.piiic.Configs;
import com.gewara.piiic.R;
import com.gewara.piiic.configs.L;
import com.gewara.piiic.cropimage.CropImageActivity;
import com.gewara.piiic.cropimage.CropResult;
import com.gewara.piiic.models.Article;
import com.gewara.piiic.models.IMageArticle;
import com.gewara.piiic.models.MemberColor;
import com.gewara.piiic.models.MemberFont;
import com.gewara.piiic.models.TextImage;
import com.gewara.piiic.task.BackgroundExecutorService;
import com.gewara.piiic.task.CreateImageTask;
import com.gewara.piiic.task.SaveImageTask;
import com.gewara.piiic.utility.FileUtils;
import com.gewara.piiic.utility.TextStyle;
import com.gewara.piiic.widgets.CustomWebView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2016/1/25.
 */
public class ArticleEditorActivity extends BaseArticleActivity {
    public static final String TAG = Configs.makeTag("ArticleEditorActivity");
    private ImageButton mAddPicButton;
    private AttachmentInterface mAttachmentInterface;
    private Button mDoneButton;
    private EditorHandler mHandler;
    protected boolean mIsTextStyleReady;
    private InputMethodManager mKeyboardManager;

    protected int getContentLayoutId() {
        return R.layout.activity_article_editor;
    }

    protected String getStaticsName() {
        return ArticleEditorActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        mDoneButton = ((Button) findViewById(R.id.activity_article_editor_done));
        mAddPicButton = ((ImageButton) findViewById(R.id.activity_article_editor_pic));
        mHandler = new EditorHandler(this);
        mKeyboardManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        TextStyle.init();
        mAddPicButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                onAddPhotoEvent();
            }
        });
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                hideKeyboard();
                tryToSaveArticle(false);
                SaveImageTask saveImageTask = new SaveImageTask(ArticleEditorActivity.this, mWebView, mArticle);
                BackgroundExecutorService.SVPIC.execute(saveImageTask);
                showToast("图片保存在SD卡PiiiC目录");
            }
        });

        mIsTextStyleReady = true;
        setUserFont();
    }

    private void addImageToEditorView(String paramString1, String paramString2, int paramInt1, int paramInt2) {
        StringBuilder localStringBuilder = new StringBuilder("javascript:addImage('");
        localStringBuilder.append(paramString1).append("','");
        if (TextUtils.isEmpty(paramString2))
            paramString2 = "imgLandwideStyle";
        localStringBuilder.append(paramString2).append("','");
        localStringBuilder.append(paramInt1).append("','");
        localStringBuilder.append(paramInt2).append("','')");
        mWebView.loadUrl(localStringBuilder.toString());
        System.gc();
    }

    private void cropPhoto(Uri paramUri) {
        CropImageActivity.startForResult(this, paramUri, 2);
    }

    private void hideKeyboard() {
        mKeyboardManager.hideSoftInputFromWindow(mWebView.getWindowToken(), 0);
    }

    private void setUserFont() {
        List localList1 = TextStyle.getFamilies();
        List localList2 = TextStyle.getColors();
        if ((this.mIsTextStyleReady) && (!localList1.isEmpty()) && (!localList2.isEmpty())) {
            WebView localWebView = this.mWebView;
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = ((MemberFont) localList1.get(0)).getName();
            arrayOfObject[1] = ((MemberColor) localList2.get(0)).getValue();
            localWebView.loadUrl(String.format("javascript:setUserFontStyle('%s', '%s')", arrayOfObject));
        }
    }

    void showKeyboard() {
        this.mKeyboardManager.showSoftInput(this.mWebView, 1);
    }

    void tryToSaveArticle(boolean paramBoolean) {
        if (!paramBoolean) {
            this.mHandler.removeMessages(2);
        } else {
            mWebView.loadUrl("javascript:abstractTextFromBody()");
            mWebView.loadUrl("javascript:getCoverImgName()");
            mWebView.loadUrl("javascript:getAllImagesName()");
            mWebView.loadUrl("javascript:originalPosterEleOuterHTML()");
            WebView localWebView = mWebView;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Boolean.valueOf(paramBoolean);
            localWebView.loadUrl(String.format("javascript:saveArticle(%s)", arrayOfObject));
        }
    }
    protected void initArticle() {
        mArticle = ((Article) getIntent().getSerializableExtra("ArticleEditorActivity.Article"));
    }

    protected void initWebView() {
        mWebView = ((WebView) findViewById(R.id.activity_article_editor_editor));
        mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString) {
                super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
                mHandler.sendEmptyMessageDelayed(1, 50L);
                loadFonts();
                mWebView.loadUrl("javascript:bindEvent4Edit()");
                setUserFont();
                mWebView.scrollTo(0, getIntent().getIntExtra("ArticleEditorActivity.ScrollY", 0));
            }
        });
        loadIndex();
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            private boolean mIsMoved;
            private Point mTouchDownPoint = new Point();

            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent) {
                switch (paramAnonymousMotionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mIsMoved = false;
                        mTouchDownPoint.set((int) paramAnonymousMotionEvent.getX(), (int) paramAnonymousMotionEvent.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if ((Math.abs(mTouchDownPoint.x - paramAnonymousMotionEvent.getX()) <= 60.0F) &&
                                (Math.abs(mTouchDownPoint.y - paramAnonymousMotionEvent.getY()) <= 60.0F)) {
                            mIsMoved = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!mIsMoved) {//mWebView获取光标焦点
                            mWebView.requestFocus();
                            mWebView.requestFocusFromTouch();
                            mWebView.loadUrl("javascript:getNodeStyle()");
                        }
                        mIsMoved = false;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        mIsMoved = false;
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        ((CustomWebView) mWebView).setOnKeyMultipleListener(new CustomWebView.OnKeyMultipleListener() {
            public void onKeyMultiple(WebView paramAnonymousWebView, String paramAnonymousString) {
                paramAnonymousWebView.loadUrl(String.format("javascript:addContent('%s')", new Object[]{paramAnonymousString}));
            }
        });
    }

    protected void loadIndex() {
        mAttachmentInterface = new AttachmentInterface(this);
        mWebView.addJavascriptInterface(mAttachmentInterface, "jsObj");
        mWebView.addJavascriptInterface(new ArticleInterface(this), "handler");
        mWebView.addJavascriptInterface(new StyleInterface(), "styleHandler");
        super.loadIndex();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);
        if (requestCode == 2 && result != null) {
            CropResult localCropResult = (CropResult) result.getParcelableExtra("CropResult");
            addImageToEditorView(localCropResult.getUri().getPath(),
                    result.getStringExtra("CropImageActivity.Style"), localCropResult.getWidth(),
                    localCropResult.getHeight());
        } else if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri localUri = result.getData();
            cropPhoto(localUri);
        }
    }

    private void onAddPhotoEvent() {
        try {
            Intent localIntent1 = new Intent("android.intent.action.PICK", null);
            localIntent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(localIntent1, 1);
        } catch (Exception localException1) {
            L.e(TAG, localException1, localException1.getMessage(), new Object[0]);
            try {
                Intent localIntent2 = new Intent();
                localIntent2.setType("image/*");
                localIntent2.setAction("android.intent.action.GET_CONTENT");
                startActivityForResult(localIntent2, 1);
            } catch (Exception localException2) {
                L.e(TAG, localException2, localException2.getMessage(), new Object[0]);
            }
        }
    }

    public void onBackPressed() {
        tryToSaveArticle(false);
    }

    protected void onDestroy() {
        mWebView.destroy();
        super.onDestroy();
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
        if (paramBoolean) {
            mWebView.requestFocus();
            mWebView.requestFocusFromTouch();
            showKeyboard();
        }
    }


    class ArticleInterface {
        private final WeakReference<Activity> mEditorRef;

        ArticleInterface(Activity activity) {
            mEditorRef = new WeakReference(activity);
        }

    }

    class EditorHandler extends Handler {
        EditorHandler(Activity activity) {
            super();
        }

        public void handleMessage(Message paramMessage) {
            switch (paramMessage.what) {
                case 1:
                    mWebView.requestFocus();
                    mWebView.requestFocusFromTouch();
                    showKeyboard();
                    break;
                case 2:
                    tryToSaveArticle(true);
                    sendEmptyMessageDelayed(2, 10000L);
                    break;
                default:
                    break;
            }

        }
    }

    class StyleInterface {
        @JavascriptInterface
        public void show(String paramString) {
            L.d(ArticleEditorActivity.TAG, "show style: %s", new Object[]{paramString});
        }
    }
    //js附件接口
    public class AttachmentInterface {
        public final List<String> mAttachments = Collections.synchronizedList(new ArrayList());
        public final WeakReference<ArticleEditorActivity> mEditorRef;
        public String mHtmlContent;
        public String mOriginalContent;
        public int mPositionY;
        public String mCoverImgName;
        public String mPoster;
        public String mTextContent;

        AttachmentInterface(ArticleEditorActivity paramArticleEditorActivity) {
            mEditorRef = new WeakReference(paramArticleEditorActivity);
        }

        @JavascriptInterface
        public void getAllImageName(String paramString) {
            L.d(ArticleEditorActivity.TAG, "getAllImageName: %s", new Object[]{paramString});
            mAttachments.clear();
            if (TextUtils.isEmpty(paramString))
                return;
            mAttachments.addAll(Arrays.asList(paramString.split(";")));
        }

        @JavascriptInterface
        public String getClipboardData() {
            ArticleEditorActivity localArticleEditorActivity = (ArticleEditorActivity) mEditorRef.get();
            if (localArticleEditorActivity == null)
                return "";
            ClipboardManager localClipboardManager = (ClipboardManager) localArticleEditorActivity.
                    getSystemService(Context.CLIPBOARD_SERVICE);
            if ((localClipboardManager.hasPrimaryClip()) && (localClipboardManager.
                    getPrimaryClipDescription().hasMimeType("text*//*")))
                return localClipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
            return "";
        }

        @JavascriptInterface
        public void getCoverImgName(String paramString) {
            L.d(ArticleEditorActivity.TAG, "getCoverImgName: %s", new Object[]{paramString});
            mCoverImgName = paramString;
        }

        @JavascriptInterface
        public void getHtmlContent(String paramString) {
            L.d(ArticleEditorActivity.TAG, "getHtmlContent: %s", new Object[]{paramString});
            mHtmlContent = paramString;
        }

        @JavascriptInterface
        public void getJSPosterInfo(String paramString) {
            L.d(ArticleEditorActivity.TAG, "getJSPosterInfo: %s", new Object[]{paramString});
            mPoster = paramString;
        }

        @JavascriptInterface
        public void getJSStyle(String paramString) {
//            L.d(ArticleEditorActivity.TAG, "getJSStyle: %s", new Object[]{paramString});
//            ArticleEditorActivity localArticleEditorActivity = (ArticleEditorActivity) mEditorRef.get();
//            if (localArticleEditorActivity != null)
//                localArticleEditorActivity.updateStyle(paramString);
        }

        @JavascriptInterface
        public void getOriginalContent(String paramString) {
            L.d(ArticleEditorActivity.TAG, "getOriginalContent: %s", new Object[]{paramString});
            mOriginalContent = paramString;
        }

        @JavascriptInterface
        public void getTextContent(String paramString) {
            L.d(ArticleEditorActivity.TAG, "getTextContent: %s", new Object[]{paramString});
            mTextContent = paramString;
        }

        @JavascriptInterface
        public void getVerticalY(int paramInt) {
            mPositionY = paramInt;
        }

        @JavascriptInterface
        public void log(String paramString) {
            L.e(ArticleEditorActivity.TAG, paramString, new Object[0]);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void madeImage(View v){

        IMageArticle mIMageArticle = new IMageArticle();
        mIMageArticle.setBitmapHeight(15000);
        mIMageArticle.setBitmapWidth(1080);
        TextImage textImage = new TextImage();
        textImage.setX(0);
        textImage.setY(0);
        textImage.setTextColor(Color.BLACK);
        textImage.setImageType(2);
        textImage.setTextContent("由于在网络上找到关于Canvas的使用都比较抽象，也许是我的逻辑思维不太好吧，总是感觉理解起来比较困难，\n" +
                "    尤其是对save()和restore()方法的使用。本篇文章的内容就是对Canvas的使用进行一下总结，包括它的两种不同的使用\n" +
                "    情节和它的一些方法进行一下说明。 \n" +
                "  \n" +
                "       1  Bitmap，可以来自资源/文件，也可以在程序中创建，实际上的功能相当于图片的存储空间；\n" +
                "       2  Canvas，紧密与Bitmap联系，把Bitmap比喻内容的话，那么Canvas就是提供了众多方法操作Bitamp的平台；\n" +
                "       3  Paint，与Canvas紧密联系，是\"画板\"上的笔刷工具，也用于设置View控件上的样式；\n" +
                "       4  Drawable，如果说前三者是看不见地在内存中画图(虚拟的)，那么Drawable就是把前三者绘图结果表现出来的接口(真实的)。\n" +
                "              Drawable多个子类，例如：位图(BitmapDrawable)、图形(ShapeDrawable)、图层(LayerDrawable)等。\n" +
                "   \n" +
                "      以上引自于hellogv的《Android入门第十四篇之画图》\n" +
                " \n" +
                "    我们打个简单的比方吧：\n" +
                "                Paint        就是画笔\n" +
                "                Bitmap    就是画布\n" +
                "                Canvas   就是画家\n" +
                " \n" +
                "     于是，画家可以通过画笔可以在画布上进行任何的画画。\n" +
                " \n" +
                "Canvas的两种使用情形，从Canvas对象的获得角度分析：\n" +
                " \n" +
                "    1、  自定义View和自定义SurfaceView中获得Canvas对象\n" +
                "       由于自定义View和SurfaceView在显示界面中已经获得了显示区域，canvas对象只不过是在其显示(绘画)区域进行界面布局\n" +
                "  的设计，当操作完毕后，系统会显示canvas的操作结果。\n" +
                " \n" +
                "       自定义View的绘图方法为：\n" +
                "[java] view plain copy print?\n" +
                "//存在canvas对象，即存在默认的显示区域  \n" +
                "    @Override  \n" +
                "    public void draw(Canvas canvas) {  \n" +
                "         //canvas绘图  \n" +
                "        }  \n" +
                "\n" +
                "      SurfaceView的绘图方法为，例如：\n" +
                "[java] view plain copy print?\n" +
                "SurfaceView  surfaceView = new MySurfaceView() ;         //创建一个Surface对象  \n" +
                "SurfaceHolder surfaceHolder = surfaceView. getHolder() ;  //获得SurfaceHolder对象  \n" +
                "Canvas   canvas  = surfaceHolder.lockCanvas() ;          //获得canvas对象  \n" +
                "//进行绘图操作  \n" +
                "surfaceHolder.unlockCanvasAndPost(canvas) ;            //释放canvas锁，并且显示视图  \n" +
                " \n" +
                "    2、  在其他情形下，我们需要通过代码创建一个Canvas对象，并且在绘画成功后，将该画图区域转换为Drawable图片\n" +
                "  或者通过setBitmap(bitmap)显现出来。一般步骤为： \n" +
                "[java] view plain copy print?\n" +
                "//创建一个的Bitmap对象   \n" +
                "  \n" +
                "   Bitmap bitmap = Bitmap.createBitmap(200, 100, Config.ARGB_8888) ;  \n" +
                "  //创建一个canvas对象，并且开始绘图  \n" +
                "   Canvas canvas = new Canvas (bitmap) ;  \n" +
                "  \n" +
                "  ImageView imgView  = new ImageView(this) ;  //或者其他可以设置背景图片的View控件  \n" +
                "   \n" +
                "  \n" +
                "   //为ImageView设置图像  \n" +
                "   //将Bitmap对象转换为Drawable图像资  \n" +
                "   Drawable drawable = new BitmapDrawable(bitmap) ;  \n" +
                "  imgView .setBackgroundDrawable(drawable) ;  \n" +
                "  \n" +
                "  \n" +
                "  或者简单点：  imgView  .setImageBitmap(bitmap);     \n" +
                "     这两种方式都可以显示我们的绘图。\n" +
                " \n" +
                " Canvas方法分析:\n" +
                " \n" +
                "         clipXXX()方法族\n" +
                "           说明：在当前的画图区域裁剪(clip)出一个新的画图区域，这个画图区域就是canvas对象的当前画图区域了。\n" +
                "              例如：clipRect(new Rect())，那么该矩形区域就是canvas的当前画图区域了。\n" +
                "        public int save()\n" +
                "           说明：保存已经由canvas绘画出来的东西，在save()和restore()方法之间的操作不对它们造成影响，例如旋转(roate)等。\n" +
                "               而且对canvas的操作(roate和translate)都是临时的，restore()后不再存在。\n" +
                "       public voidrestore()\n" +
                "           说明：复原sava()方法之前保存的东西资源。\n" +
                "       drawXXX()方法族\n" +
                "           说明：以一定的坐标值在当前画图区域画图。\n" +
                "           注意：图层会叠加，即后面绘画的图层会覆盖前面绘画的图层。\n" +
                " \n" +
                " 需要注意的方法是：\n" +
                "     public voiddrawRect(float left, float top, float right, float bottom,Paint paint)\n" +
                "           说明：绘制一个矩型。需要注明的是绘制矩形的参数和Java中的方法不一样。\n" +
                "              该方法的参数图解说明如下：\n" +
                " ");
        textImage.setTextSize(20);
        List<TextImage>textImageList = new ArrayList<TextImage>();
        textImageList.add(textImage);
         textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setWidth(600);
        textImage.setHeight(400);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/0.jpg");
        textImageList.add(textImage);
        textImage = new TextImage();
        textImage.setImageType(2);
        textImage.setTextColor(Color.BLACK);
        textImage.setTextSize(30);
        textImage.setTextContent("可以看到这个矩形的左上角位置为（8，-90），右下角的位置为（634，26）；\n" +
                "大家可能会有疑问，为什么左上角的Y坐标是个负数？从代码中，我们也可以看到，我们并没有给getTextBounds（）传递基线位置。那它就是以（0，0）为基线来得到这个最小矩形的！所以这个最小矩形的位置就是以（0，0）为基线的结果！");
        textImageList.add(textImage);
        textImage = new TextImage();
        textImage.setImageType(2);
        textImage.setTextColor(Color.RED);
        textImage.setTextSize(40);
        textImage.setTextContent("可以看到这个矩形的左上角位置为（8，-90），右下角的位置为（634，26）；\n" +
                "大家可能会有疑问，为什么左上角的Y坐标是个负数？从代码中，我们也可以看到，我们并没有给getTextBounds（）传递基线位置。那它就是以（0，0）为基线来得到这个最小矩形的！所以这个最小矩形的位置就是以（0，0）为基线的结果！");
        textImageList.add(textImage);
        textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/1.jpg");
        textImageList.add(textImage);
        textImage = new TextImage();
        textImage.setImageType(2);
        textImage.setTextColor(Color.GRAY);
        textImage.setTextSize(50);
        textImage.setTextContent("可以看到这个矩形的左上角位置为（8，-90），右下角的位置为（634，26）；\n" +
                "大家可能会有疑问，为什么左上角的Y坐标是个负数？从代码中，我们也可以看到，我们并没有给getTextBounds（）传递基线位置。那它就是以（0，0）为基线来得到这个最小矩形的！所以这个最小矩形的位置就是以（0，0）为基线的结果！");
        textImageList.add(textImage);
        textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/2.jpg");
        textImageList.add(textImage);
        textImage = new TextImage();
        textImage.setImageType(2);
        textImage.setTextColor(Color.BLUE);
        textImage.setTextSize(60);
        textImage.setTextContent("可以看到这个矩形的左上角位置为（8，-90），右下角的位置为（634，26）；\n" +
                "大家可能会有疑问，为什么左上角的Y坐标是个负数？从代码中，我们也可以看到，我们并没有给getTextBounds（）传递基线位置。那它就是以（0，0）为基线来得到这个最小矩形的！所以这个最小矩形的位置就是以（0，0）为基线的结果！");
        textImageList.add(textImage);
        textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/0.jpg");
        textImageList.add(textImage);
        textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/1.jpg");
        textImageList.add(textImage);
        textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/2.jpg");
        textImageList.add(textImage);
        textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/3.jpg");
        textImageList.add(textImage);textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/4.jpg");
        textImageList.add(textImage);textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/5.jpg");
        textImageList.add(textImage);textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/6.jpg");
        textImageList.add(textImage);textImage = new TextImage();
        textImage.setImageType(1);
        textImage.setFilePath(Environment.getExternalStorageDirectory() + "/comweibo/7.jpg");
        textImageList.add(textImage);
        Intent intent = new Intent(ArticleEditorActivity.this,ArticleEditorActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        mIMageArticle.setTextImages(textImageList);
        CreateImageTask saveImageTask = new CreateImageTask(ArticleEditorActivity.this, mIMageArticle);
        BackgroundExecutorService.SVPIC.execute(saveImageTask);
        showToast("图片保存在SD卡PiiiC目录");
    }
}
