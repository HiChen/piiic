package com.gewara.piiic.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import com.gewara.piiic.Configs;
import com.gewara.piiic.models.Article;
import com.gewara.piiic.models.MemberFont;
import com.gewara.piiic.utility.TextStyle;

import java.util.Iterator;


/**
 * Created by user on 2016/1/25.
 */
public abstract class BaseArticleActivity extends BaseActivity {
    private static final String TAG = Configs.makeTag("BaseArticleActivity");
    protected Article mArticle;
//    protected Paper mPaper;
    protected WebView mWebView;
//    protected AccessTokenPreferences accessTokenPreferences;

    /*protected void changePaperStyle() {
        if (mPaper == null) {
            return;
        }
        if (mPaper.isColor()) {
            WebView localWebView3 = mWebView;
            Object[] arrayOfObject3 = new Object[1];
            arrayOfObject3[0] = mPaper.getDetail();
            localWebView3.loadUrl(String.format("javascript:changeBodyBgColor('%s')", arrayOfObject3));
            return;
        }


        if (!mPaper.isTexture()) {
            File localFile = CacheUtil.getPaperFile(this, mPaper.getDetail());
            if ((localFile != null) && (localFile.isFile()) && (localFile.exists())) {
                WebView localWebView2 = mWebView;
                Object[] arrayOfObject2 = new Object[1];
                arrayOfObject2[0] = localFile.getAbsolutePath();
                localWebView2.loadUrl(String.format("javascript:changeArticleBodyBgImage('%s')", arrayOfObject2));
                return;
            }
            WebView localWebView1 = mWebView;
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = mPaper.getDetail();
            localWebView1.loadUrl(String.format("javascript:changeArticleBodyBgImage('%s')", arrayOfObject1));
        }
    }
*/
    protected abstract void initArticle();

    protected abstract void initWebView();

   /* protected void loadArticle(final boolean paramBoolean) {
        final int i = mArticle.getArticleId();
        AsyncDatabaseHelper localAsyncDatabaseHelper = PiiicDB.A_ATTACHMENT;
        DatabaseCallback local1 = new DatabaseCallback() {
            @Override
            public void onCompleted(Object paramT) {
                final List paramAnonymousList = (List) paramT;
                BackgroundExecutorService.OTHER.execute(new BackgroundTask() {
                    public void postExecute(List<Attachment> paramAnonymous2List) {
                        super.postExecute(paramAnonymous2List);
                        String str1 = mArticle.getContent();
                        String str2 = "";
                        if (str1 == null) {
                            return;
                        }

                        if (!TextUtils.isEmpty(str2)){
                            str2 =str1.trim().replace("'","");
                        }
                        Iterator localIterator = paramAnonymous2List.iterator();
                        while (localIterator.hasNext()) {
                            Attachment localAttachment = (Attachment) localIterator.next();
                            String str3 = localAttachment.getUrl();
                            String str4 = localAttachment.getLocalPath();
                            if ((!TextUtils.isEmpty(str3)) && (!TextUtils.isEmpty(str4)))
                                str2 = str2.replace(str3, str4);
                        }

                        L.d(BaseArticleActivity.TAG, "load article content: %s", new Object[]{str2});
                        WebView localWebView = mWebView;
                        Object[] arrayOfObject = new Object[2];
                        arrayOfObject[0] = str2;
                        arrayOfObject[1] = Boolean.valueOf(mArticle.isInTrash());
                        localWebView.loadUrl(String.format("javascript:initContentWithString('%s', '%s')", arrayOfObject));
                        if (mArticle.isInTrash()) mWebView.loadUrl("javascript:getOriginalContent()");
                        loadPaper();
                    }

                    @Override
                    public List<Attachment> runTask() {
                        ArrayList localArrayList = new ArrayList(paramAnonymousList.size());
                        Iterator localIterator = paramAnonymousList.iterator();
                        while (localIterator.hasNext()) {
                            Attachment localAttachment = (Attachment) localIterator.next();
                            if ((TextUtils.isEmpty(localAttachment.getLocalPath())) && (!TextUtils.isEmpty(localAttachment.getUrl()))) {
                                String str1 = localAttachment.getUrl();
                                boolean bool = FileDownloader.downloadSync(str1, CacheUtil.getAttachmentsDir(BaseArticleActivity.this, Integer.valueOf(
                                        mArticle.getArticleId())), null);
                                String str2 = BaseArticleActivity.TAG;
                                Object[] arrayOfObject = new Object[3];
                                arrayOfObject[0] = Integer.valueOf(mArticle.getArticleId());
                                arrayOfObject[1] = Boolean.valueOf(bool);
                                arrayOfObject[2] = str1;
                                L.d(str2, "download attachment for article[%d]: %s - %s", arrayOfObject);
                                if (bool) {
                                    localAttachment.setLocalPath(CacheUtil.getAttachmentFile(BaseArticleActivity.this, mArticle.getArticleId(), str1).getAbsolutePath());
                                    localAttachment.setSync(1);
                                    localArrayList.add(localAttachment);
                                    DatabaseHelper localDatabaseHelper = PiiicDB.ATTACHMENT;
                                    String str3 = String.format("%s=?", new Object[]{"_id"});
                                    String[] arrayOfString = new String[1];
                                    arrayOfString[0] = String.valueOf(localAttachment.getId());
                                    localDatabaseHelper.update(localAttachment, str3, arrayOfString);
                                }
                            } else {
                                localArrayList.add(localAttachment);
                            }
                        }
                        return localArrayList;
                    }
                });
            }
        };
        String str = String.format("%s=? and %s=?", new Object[]{"_article_id", "_owner_email"});
        String[] arrayOfString = new String[2];
        arrayOfString[0] = String.valueOf(mArticle.getArticleId());
        arrayOfString[1] = accessTokenPreferences.getEmail();
        localAsyncDatabaseHelper.query(local1, str, arrayOfString);
    }
*/
    protected void loadFonts() {
        StringBuilder localStringBuilder = new StringBuilder();
        Iterator localIterator = TextStyle.getFamilies().iterator();
        while (localIterator.hasNext()) {
            MemberFont localMemberFont = (MemberFont) localIterator.next();
            if (!localMemberFont.isDefault()) {
                WebView localWebView2 = mWebView;
                Object[] arrayOfObject2 = new Object[2];
                arrayOfObject2[0] = localMemberFont.getName();
                Object[] arrayOfObject3 = new Object[1];
                arrayOfObject3[0] = localMemberFont.getLocalPath();
                arrayOfObject2[1] = String.format("file://%s", arrayOfObject3);
                localWebView2.loadUrl(String.format("javascript:fontFamilyStyleAdd('%s','%s')", arrayOfObject2));
            }
            localStringBuilder.append(localMemberFont.getName()).append(",");
        }
        int i = localStringBuilder.length();
        if (i > 0) {
            localStringBuilder.deleteCharAt(i - 1);
            WebView localWebView1 = mWebView;
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = localStringBuilder.toString();
            localWebView1.loadUrl(String.format("javascript:loadFonts('%s')", arrayOfObject1));
        }
    }

    @SuppressLint({"setJavaScriptEnabled"})
    protected void loadIndex() {
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/index.html");
    }

//    protected void loadPaper() {
//        String str1 = mArticle.getStyle();
//        if (!TextUtils.isEmpty(str1)) {
//            AsyncDatabaseHelper localAsyncDatabaseHelper = PiiicDB.A_PAPER;
//            DatabaseCallback local2 = new DatabaseCallback() {
//
//                @Override
//                public void onCompleted(Object paramT) {
//                    Paper paramAnonymousPaper = (Paper) paramT;
//                    mPaper = paramAnonymousPaper;
//                    changePaperStyle();
//                }
//            };
//            String str2 = String.format("%s=? and %s=?", new Object[]{"_owner_email", "_name"});
//            String[] arrayOfString = new String[2];
//            arrayOfString[0] = "515928615@qq.com";
//            arrayOfString[1] = str1;
//            localAsyncDatabaseHelper.queryFirst(local2, str2, arrayOfString);
//        }
//    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
//        accessTokenPreferences = AccessTokenPreferences.getInstance(this);
        initArticle();
        initWebView();
    }


}