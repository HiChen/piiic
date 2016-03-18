package com.gewara.piiic.models;

import com.google.gson.annotations.SerializedName;
import nl.qbusict.cupboard.annotation.Column;

public class Attachment extends BaseModel
{
    public static final String C_ARTICLE_ID = "_article_id";
    public static final String C_ATTACHMENT_ID = "_attachment_id";
    public static final String C_LOCAL_ARTICLE_ID = "_local_article_id";
    public static final String C_LOCAL_PATH = "_local_path";
    public static final String C_ORDER = "_order";
    public static final String C_URL = "_url";
    public static final int DEFAULT_ARTICLE_ID = -1;
    public static final String LOCAL_PATH_PREFIX = "file://";

    @SerializedName("__article_id")
    @Column("_article_id")
    private int mArticleId = -1;

    @SerializedName("id")
    @Column("_attachment_id")
    private int mAttachmentId;

    @SerializedName("__local_article_id")
    @Column("_local_article_id")
    private long mLocalArticleId;

    @SerializedName("__local_path")
    @Column("_local_path")
    private String mLocalPath;

    @SerializedName("__order")
    @Column("_order")
    private int mOrder;

    @SerializedName("size")
    @Column("_size")
    private int mSize;

    @SerializedName("attachment_url")
    @Column("_url")
    private String mUrl;

    public int getArticleId()
    {
        return this.mArticleId;
    }

    public int getAttachmentId()
    {
        return this.mAttachmentId;
    }

    public long getLocalArticleId()
    {
        return this.mLocalArticleId;
    }

    public String getLocalPath()
    {
        return this.mLocalPath;
    }

    public int getOrder()
    {
        return this.mOrder;
    }

    public int getSize()
    {
        return this.mSize;
    }

    public String getUrl()
    {
        return this.mUrl;
    }

    public void setArticleId(int paramInt)
    {
        this.mArticleId = paramInt;
    }

    public void setAttachmentId(int paramInt)
    {
        this.mAttachmentId = paramInt;
    }

    public void setLocalArticleId(long paramLong)
    {
        this.mLocalArticleId = paramLong;
    }

    public void setLocalPath(String paramString)
    {
        this.mLocalPath = paramString;
    }

    public void setOrder(int paramInt)
    {
        this.mOrder = paramInt;
    }

    public void setSize(int paramInt)
    {
        this.mSize = paramInt;
    }

    public void setUrl(String paramString)
    {
        this.mUrl = paramString;
    }

    public void updateFrom(BaseModel paramBaseModel)
    {
        if ((paramBaseModel == null) || (!(paramBaseModel instanceof Attachment)))
            return;
        Attachment localAttachment = (Attachment)paramBaseModel;
        this.mAttachmentId = localAttachment.mAttachmentId;
        this.mArticleId = localAttachment.mArticleId;
        this.mUrl = localAttachment.mUrl;
        this.mSize = localAttachment.mSize;
    }
}
