package com.gewara.piiic.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import nl.qbusict.cupboard.annotation.Column;

/**
 * Created by user on 2016/1/25.
 */
public class Paper extends BaseModel  implements Serializable
{
    private static final String COLOR = "color";
    public static final String C_NAME = "_name";
    public static final String C_ORDER = "_order";
    public static final String C_PAPER_ID = "_paper_id";
    public static final String DEFAULT_NAME = "default";
    public static final String KEY_EXTRA = "Paper";
    private static final String TEXTURE = "texture";

    @SerializedName("css")
    @Column("_css")
    private String mCss;

    @SerializedName("description")
    @Column("_description")
    private String mDescription;

    @SerializedName("detail")
    @Column("_detail")
    private String mDetail;

    @SerializedName("modified")
    @Column("_modified")
    private String mModified;

    @SerializedName("name")
    @Column("_name")
    private String mName;

    @SerializedName("order")
    @Column("_order")
    private int mOrder;

    @SerializedName("id")
    @Column("_paper_id")
    private int mPaperId;

    @SerializedName("type")
    @Column("_type")
    private String mType;

    public Paper()
    {
    }

    public Paper(String paramString1, String paramString2, Integer paramInteger, String paramString3, String paramString4, String paramString5, String paramString6)
    {
        this.mName = paramString1;
        this.mModified = paramString2;
        this.mOrder = paramInteger.intValue();
        this.mType = paramString3;
        this.mDescription = paramString4;
        this.mDetail = paramString5;
        this.mCss = paramString6;
    }

    public String getCss()
    {
        return this.mCss;
    }

    public String getDescription()
    {
        return this.mDescription;
    }

    public String getDetail()
    {
        return this.mDetail;
    }

    public String getModified()
    {
        return this.mModified;
    }

    public String getName()
    {
        return this.mName;
    }

    public Integer getOrder()
    {
        return Integer.valueOf(this.mOrder);
    }

    public int getPaperId()
    {
        return this.mPaperId;
    }

    public String getType()
    {
        return this.mType;
    }

    public boolean isColor()
    {
        return "color".equalsIgnoreCase(this.mType);
    }

    public boolean isTexture()
    {
        return "texture".equalsIgnoreCase(this.mType);
    }

    public void setCss(String paramString)
    {
        this.mCss = paramString;
    }

    public void setDescription(String paramString)
    {
        this.mDescription = paramString;
    }

    public void setDetail(String paramString)
    {
        this.mDetail = paramString;
    }

    public void setModified(String paramString)
    {
        this.mModified = paramString;
    }

    public void setName(String paramString)
    {
        this.mName = paramString;
    }

    public void setOrder(Integer paramInteger)
    {
        this.mOrder = paramInteger.intValue();
    }

    public void setPaperId(int paramInt)
    {
        this.mPaperId = paramInt;
    }

    public void setType(String paramString)
    {
        this.mType = paramString;
    }

    public void updateFrom(BaseModel paramBaseModel)
    {
        if ((paramBaseModel == null) || (!(paramBaseModel instanceof Paper)))
            return;
        Paper localPaper = (Paper)paramBaseModel;
        this.mPaperId = localPaper.mPaperId;
        this.mName = localPaper.mName;
        this.mModified = localPaper.mModified;
        this.mOrder = localPaper.mOrder;
        this.mType = localPaper.mType;
        this.mDescription = localPaper.mDescription;
        this.mDetail = localPaper.mDetail;
        this.mCss = localPaper.mCss;
    }
}