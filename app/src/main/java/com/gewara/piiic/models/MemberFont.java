package com.gewara.piiic.models;

/**
 * Created by user on 2016/1/26.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import nl.qbusict.cupboard.annotation.Column;

public class MemberFont extends BaseModel
        implements Comparable<MemberFont> {
    public static final String C_IS_DEFAULT = "_is_default";
    public static final String C_NAME = "_font_name";
    public static final String C_ORDER = "_order";
    public static final int DEFAULT_COUNT = 12;
    public static final ArrayList<String> DEFAULT_FONTS = new ArrayList();
    private transient int mIndex;

    @SerializedName("__is_default")
    @Column("_is_default")
    private boolean mIsDefault;

    @SerializedName("local_font_name")
    @Column("_local_font_name")
    private String mLocalName;

    @SerializedName("__local_path")
    @Column("_local_path")
    private String mLocalPath;

    @SerializedName("modified")
    @Column("_modified")
    private Date mModified;

    @SerializedName("font_name")
    @Column("_font_name")
    private String mName;

    @SerializedName("order")
    @Column("_order")
    private int mOrder;

    @SerializedName("font_url")
    @Column("_font_url")
    private String mUrl;

    static {
        DEFAULT_FONTS.addAll(Arrays.asList(new String[]{"Heiti SC", "Kaiti SC", "Libian SC", "Xingkai SC", "Wawati SC", "Helvetica", "Times New Roman", "Cochin", "Avenir Next Condensed", "Futura", "Bradley Hand", "Savoye LET"}));
    }

    public static MemberFont createBy(MemberFile paramMemberFile) {
        MemberFont localMemberFont = new MemberFont();
        localMemberFont.mName = paramMemberFile.getName();
        localMemberFont.mLocalName = paramMemberFile.getDescription();
        localMemberFont.mUrl = paramMemberFile.getSrc();
        localMemberFont.mOrder = paramMemberFile.getOrder();
        localMemberFont.mModified = paramMemberFile.getModified();
        localMemberFont.mIsDefault = localMemberFont.checkIsDefault();
        return localMemberFont;
    }

    public boolean checkIsDefault() {
        return DEFAULT_FONTS.contains(this.mName);
    }

    public int compareTo(MemberFont paramMemberFont) {
        if (paramMemberFont == null) {
            return -1;
        }
        if (this.mOrder > paramMemberFont.mOrder) {
            return 1;

        }
        if (this.mOrder < paramMemberFont.mOrder) {
            return 0;
        }
        return -1;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public String getKey() {
        return this.mName;
    }

    public String getLocalName() {
        return this.mLocalName;
    }

    public String getLocalPath() {
        return this.mLocalPath;
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

    public String getUrl() {
        return this.mUrl;
    }

    public boolean isDefault() {
        return this.mIsDefault;
    }

    public void setIndex(int paramInt) {
        this.mIndex = paramInt;
    }

    public void setIsDefault(boolean paramBoolean) {
        this.mIsDefault = paramBoolean;
    }

    public void setLocalName(String paramString) {
        this.mLocalName = paramString;
    }

    public void setLocalPath(String paramString) {
        this.mLocalPath = paramString;
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

    public void setUrl(String paramString) {
        this.mUrl = paramString;
    }

    public void updateFrom(BaseModel paramBaseModel) {
        if (!(paramBaseModel instanceof MemberFont))
            return;
        MemberFont localMemberFont = (MemberFont) paramBaseModel;
        this.mLocalName = localMemberFont.getLocalName();
        this.mName = localMemberFont.getName();
        this.mUrl = localMemberFont.getUrl();
        this.mOrder = localMemberFont.getOrder();
        this.mModified = localMemberFont.getModified();
        this.mIsSync = localMemberFont.mIsSync;
        this.mIsDefault = localMemberFont.checkIsDefault();
        this.mOwnerEmail = localMemberFont.mOwnerEmail;
    }
}