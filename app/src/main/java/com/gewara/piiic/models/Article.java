package com.gewara.piiic.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import nl.qbusict.cupboard.annotation.Column;
import nl.qbusict.cupboard.annotation.Ignore;

/**
 * Created by user on 2016/1/25.
 */
public class Article extends BaseModel implements Comparable<Article> {
    public static final String C_ARTICLE_ID = "_article_id";
    public static final String C_IS_CONFLICT = "_is_conflict";
    public static final int DEFAULT_ARTICLE_ID = -1;
    public static final String KEY_EXTRA = "Article.Extra";

    @SerializedName("id")
    @Column("_article_id")
    private int mArticleId = -1;

        @SerializedName("client_create_date")
    @Column("_client_create_date")
    private Date mClientCreated;

        @SerializedName("client_modify_date")
    @Column("_client_modify_date")
    private Date mClientModified;

        @SerializedName("__color")
    @Column("_color")
    private int mColor;

        @SerializedName("content")
    @Column("_content")
    private String mContent;

        @SerializedName("cover_url")
    @Column("_cover_url")
    private String mCoverUrl;

        @SerializedName("created")
    @Column("_created")
    private Date mCreated;

        @SerializedName("description")
    @Column("_description")
    private String mDescription;

        @SerializedName("display_html")
    @Column("_display_html")
    private String mDisplayHtml;

        @SerializedName("hits")
    @Column("_hits")
    private int mHits;

        @SerializedName("__is_conflict")
    @Column("_is_conflict")
    private boolean mIsConflict;

        @SerializedName("is_in_trash")
    @Ignore
    private boolean mIsInTrash;

        @SerializedName("is_public")
    @Column("_is_public")
    private boolean mIsPublic;

        @SerializedName("is_removed")
    @Ignore
    private boolean mIsRemoved;

        @SerializedName("__local_cover")
    @Column("_local_cover")
    private String mLocalCover;

        @SerializedName("modified")
    @Column("_modified")
    private Date mModified;

        @SerializedName("share_url")
    @Column("_share_url")
    private String mShareUrl;

        @SerializedName("shared_marks")
    @Column("_shared_marks")
    private String[] mSharedMarks;

        @SerializedName("style")
    @Column("_style")
    private String mStyle;

        @SerializedName("tags")
    @Column("_tags")
    private String[] mTags;

        @SerializedName("title")
    @Column("_title")
    private String mTitle;

        @SerializedName("word_count")
    @Column("_word_count")
    private int mWordCount;

    public int compareTo(Article paramArticle) {
        if (paramArticle == null) ;
        Date localDate = paramArticle.getClientModified();
        if (localDate == null) {
            return 1;
        }
        if ((this.mClientModified == null) && (localDate == null))
            return 0;
        if (this.mClientModified == null)
            return -1;
        if (this.mClientModified.before(localDate))
            return -1;
        if (this.mClientModified.after(localDate)) ;
        return 0;
    }

    public int getArticleId() {
        return this.mArticleId;
    }

    public Date getClientCreated() {
        return this.mClientCreated;
    }

    public Date getClientModified() {
        return this.mClientModified;
    }

    public int getColor() {
        return this.mColor;
    }

    public String getContent() {
        return this.mContent;
    }

    public String getCoverUrl() {
        return this.mCoverUrl;
    }

    public Date getCreated() {
        return this.mCreated;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getDisplayHtml() {
        return this.mDisplayHtml;
    }

    public int getHits() {
        return this.mHits;
    }

    public String getLocalCover() {
        return this.mLocalCover;
    }

    public Date getModified() {
        return this.mModified;
    }

    public String getShareUrl() {
        return this.mShareUrl;
    }

    public String[] getSharedMarks() {
        return this.mSharedMarks;
    }

    public String getStyle() {
        return this.mStyle;
    }

    public String[] getTags() {
        return this.mTags;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getWordCount() {
        return this.mWordCount;
    }

    public boolean isCard() {
        return "card".equalsIgnoreCase(this.mStyle);
    }

    public boolean isConflict() {
        return this.mIsConflict;
    }

    public boolean isInTrash() {
        return this.mIsInTrash;
    }

    public boolean isPublic() {
        return this.mIsPublic;
    }

    public boolean isRemoved() {
        return this.mIsRemoved;
    }

    public void setArticleId(int paramInt) {
        this.mArticleId = paramInt;
    }

    public void setClientCreated(Date paramDate) {
        this.mClientCreated = paramDate;
    }

    public void setClientModified(Date paramDate) {
        this.mClientModified = paramDate;
    }

    public void setColor(int paramInt) {
        this.mColor = paramInt;
    }

    public void setContent(String paramString) {
        this.mContent = paramString;
    }

    public void setCoverUrl(String paramString) {
        this.mCoverUrl = paramString;
    }

    public void setCreated(Date paramDate) {
        this.mCreated = paramDate;
    }

    public void setDescription(String paramString) {
        this.mDescription = paramString;
    }

    public void setDisplayHtml(String paramString) {
        this.mDisplayHtml = paramString;
    }

    public void setHits(int paramInt) {
        this.mHits = paramInt;
    }

    public void setIsConflict(boolean paramBoolean) {
        this.mIsConflict = paramBoolean;
    }

    public void setIsInTrash(boolean paramBoolean) {
        this.mIsInTrash = paramBoolean;
    }

    public void setIsRemoved(boolean paramBoolean) {
        this.mIsRemoved = paramBoolean;
    }

    public void setLocalCover(String paramString) {
        this.mLocalCover = paramString;
    }

    public void setModified(Date paramDate) {
        this.mModified = paramDate;
    }

    public void setPublic(boolean paramBoolean) {
        this.mIsPublic = paramBoolean;
    }

    public void setShareUrl(String paramString) {
        this.mShareUrl = paramString;
    }

    public void setSharedMarks(String[] paramArrayOfString) {
        this.mSharedMarks = paramArrayOfString;
    }

    public void setStyle(String paramString) {
        this.mStyle = paramString;
    }

    public void setTags(String[] paramArrayOfString) {
        this.mTags = paramArrayOfString;
    }

    public void setTitle(String paramString) {
        this.mTitle = paramString;
    }

    public void setWordCount(int paramInt) {
        this.mWordCount = paramInt;
    }

    public void updateFrom(BaseModel paramBaseModel) {
        if ((paramBaseModel == null) || (!(paramBaseModel instanceof Article))) {
            return;
        }
        Article  localArticle = (Article) paramBaseModel;
        if (!localArticle.isSync());
            this.mArticleId = localArticle.mArticleId;
            this.mTitle = localArticle.mTitle;
            this.mCreated = localArticle.mCreated;
            this.mModified = localArticle.mModified;
            this.mClientCreated = localArticle.mClientCreated;
            this.mClientModified = localArticle.mClientModified;
            this.mDescription = localArticle.mDescription;
            this.mStyle = localArticle.mStyle;
            this.mTags = localArticle.mTags;
            this.mCoverUrl = localArticle.mCoverUrl;
            this.mIsPublic = localArticle.mIsPublic;
            this.mSharedMarks = localArticle.mSharedMarks;
            this.mHits = localArticle.mHits;
            this.mWordCount = localArticle.mWordCount;
            this.mContent = localArticle.mContent;
            this.mDisplayHtml = localArticle.mDisplayHtml;
            this.mShareUrl = localArticle.mShareUrl;
            this.mIsConflict = localArticle.mIsConflict;
            this.mLocalCover = localArticle.mLocalCover;

    }

    public void updateFromNewArticle(Article paramArticle) {
        setArticleId(paramArticle.getArticleId());
        setCreated(paramArticle.getCreated());
        setModified(paramArticle.getModified());
        setShareUrl(paramArticle.getShareUrl());
        setStyle(paramArticle.getStyle());
    }
}
