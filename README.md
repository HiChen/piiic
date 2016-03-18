# piiic 长微博（长图文转图片功能）

二月份自己研究了下长微博的生成，在网上找了很久也没找到相关的资料，没办法就有下载相关的app打开来看，经过多天的研究和参照已有的app总算是整了个最简单的长图生成器。

下面来介绍下它的大致思路

   1、长图文编辑器：编辑器主要是利用webView与js的相互调用来成为一个编辑器，类似与EditText可文字和图片插入，其实EditText也可以实现图文插入编辑，但没有webView与js

结合这么灵活。

css文件为字体样式、大小、颜色、等 ,js为供WebView调用的相关方法

ArticleEditorActivity为编辑器，CropImageActivity为图片编辑器（剪切图片）

ArticleEditorActivity中主要包含webView组件，

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
　　　　　　　　　　//从相册中获取图片
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

　　//调用js文件中的javascript：addImage把图片插入到webView中
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

    //隐藏软件盘
    private void hideKeyboard() {
        mKeyboardManager.hideSoftInputFromWindow(mWebView.getWindowToken(), 0);
    }
   
   //设置字体大小
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
   
   //显示软件盘
    void showKeyboard() {
        this.mKeyboardManager.showSoftInput(this.mWebView, 1);
    }
    
    //初始化webview
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
      //文字输入
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

    private void onAddPhotoEvent() {//调用系统相册相关的程序
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
}

开始编辑如图，点击完成保存图片，在输入很多内容和图片后mWebView的高度会变的非常的大，这时要生成一张长图要首先获取实际的

mWebView的高度，如果我们就用mWebView.getHeight()的话我们获得的只能是屏幕范围内的高度，不是我们要的实际

高度，有人肯定说要mWebView.getMeasuredHeight()其实它和mWebView.getHeight（）获取的高度是一样的，那改

如何去获取？经过多次尝试需要使用mWebView.getContentHeight()*context.getScale()这样才是mWebView组件的

实际高度。

高度是获取到了接下来需要生成一个同等高度的Bitmap了，这时我们不得不要考虑oom问题了，内存过大或内存溢出都是

我们不想看到的，得想办法控制看下面段代码

 int n = localBitmap1.getHeight();
int i1 = 2 * getMaxMemory() / 3 / getBytesPerPixel(Bitmap.Config.RGB_565) / i;
int i2 = Math.min(Math.max(height, width), i1);
String str2 = TAG;
Object[] arrayOfObject2 = new Object[2];
arrayOfObject2[0] = Integer.valueOf(i1);
arrayOfObject2[1] = Integer.valueOf(i2);
L.d(str2, "Webview maxHeight: %d, bitmapHeight: %d", arrayOfObject2);

Bitmap localBitmap2 = Bitmap.createBitmap(i, i2, Bitmap.Config.RGB_565);
Canvas localCanvas1 = new Canvas(localBitmap2);
通过内存与图片的宽高的临界值获取最适合的高度，再者就是把图片改为RGB_565，这样bitmap要小很多

在保存bitmap时

FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
paramBitmap.compress(CompressFormat.JPEG, 50, localFileOutputStream);
localFileOutputStream.flush();
localFileOutputStream.close();
paramBitmap.recycle();
以jpg格式，质量在减小一半，这样一个长图就完成了，但bitmap用完了即使recycle()了
内存也不见回收，这时我们需要手动调用System.gc（）回收下就好了.
