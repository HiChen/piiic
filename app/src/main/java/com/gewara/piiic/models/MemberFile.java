package com.gewara.piiic.models;

/**
 * Created by user on 2016/1/26.
 */

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MemberFile {
    public static final String TYPE_COLOR = "color";
    public static final String TYPE_FONT = "font";

    @SerializedName("description")
    private String mDescription;
    private transient int mDownloadPercent;

    @SerializedName("id")
    private int mId;

    @SerializedName("modified")
    private Date mModified;

    @SerializedName("name")
    private String mName;

    @SerializedName("order")
    private int mOrder;

    @SerializedName("preview")
    private String mPreview;

    @SerializedName("src")
    private String mSrc;

    @SerializedName("type")
    private String mType;

    public boolean equals(Object paramObject) {
        if (this == paramObject) ;
        MemberFile localMemberFile = (MemberFile) paramObject;
        if ((paramObject == null) || (getClass() != paramObject.getClass()))
            return false;
        if (this.mId == localMemberFile.mId) ;
        return true;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getDownloadPercent() {
        return this.mDownloadPercent;
    }

    public int getId() {
        return this.mId;
    }

    public Date getModified() {
        return this.mModified;
    }

    public String getName() {
        return this.mName;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public String getPreview() {
        return this.mPreview;
    }

    public String getSrc() {
        return this.mSrc;
    }

    public String getType() {
        return this.mType;
    }

    public int hashCode() {
        return this.mId;
    }

    public boolean isColor() {
        return "color".equalsIgnoreCase(this.mType);
    }

    public boolean isFont() {
        return "font".equalsIgnoreCase(this.mType);
    }

    public void setDescription(String paramString) {
        this.mDescription = paramString;
    }

    public void setDownloadPercent(int paramInt) {
        this.mDownloadPercent = Math.max(0, Math.min(100, paramInt));
    }

    public void setId(int paramInt) {
        this.mId = paramInt;
    }

    public void setModified(Date paramDate) {
        this.mModified = paramDate;
    }

    public void setName(String paramString) {
        this.mName = paramString;
    }

    public void setOrder(int paramInt) {
        this.mOrder = paramInt;
    }

    public void setPreview(String paramString) {
        this.mPreview = paramString;
    }

    public void setSrc(String paramString) {
        this.mSrc = paramString;
    }

    public void setType(String paramString) {
        this.mType = paramString;
    }
}