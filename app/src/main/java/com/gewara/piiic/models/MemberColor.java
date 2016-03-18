package com.gewara.piiic.models;
/**
 * Created by user on 2016/1/26.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import nl.qbusict.cupboard.annotation.Column;

public class MemberColor extends BaseModel
        implements Comparable<MemberColor> {
    public static final String C_COLOR = "_color_value";
    public static final String C_IS_DEFAULT = "_is_default";
    public static final String C_ORDER = "_order";
    public static final ArrayList<String> DEFAULT_COLORS = new ArrayList();
    public static final int DEFAULT_COUNT = 12;

    @SerializedName("color")
    @Column("_color")
    private String mColor;
    private transient int mIndex;

    @SerializedName("__is_default")
    @Column("_is_default")
    private boolean mIsDefault;

    @SerializedName("modified")
    @Column("_modified")
    private Date mModified;

    @SerializedName("order")
    @Column("_order")
    private int mOrder;

    @SerializedName("color_value")
    @Column("_color_value")
    private String mValue;

    static {
        DEFAULT_COLORS.addAll(Arrays.asList(new String[]{"rgba(32,32,32,1.0)", "rgba(172,172,172,1.0)", "rgba(240,240,240,1.0)", "rgba(56,140,228,1.0)", "rgba(46,166,155,1.0)", "rgba(57,181,74,1.0)", "rgba(255,138,0,1.0)", "rgba(237,35,8,1.0)", "rgba(199,0,43,1.0)", "rgba(176,79,187,1.0)", "rgba(46,49,146,1.0)", "rgba(117,76,36,1.0)"}));
    }

    public static MemberColor createBy(MemberFile paramMemberFile) {
        MemberColor localMemberColor = new MemberColor();
        localMemberColor.mColor = paramMemberFile.getName();
        localMemberColor.mValue = paramMemberFile.getDescription();
        localMemberColor.mOrder = paramMemberFile.getOrder();
        localMemberColor.mModified = paramMemberFile.getModified();
        localMemberColor.mIsDefault = localMemberColor.checkIsDefault();
        return localMemberColor;
    }

    public boolean checkIsDefault() {
        return DEFAULT_COLORS.contains(this.mValue);
    }

    public int compareTo(MemberColor paramMemberColor) {
        if (paramMemberColor == null) {
            return -1;
        }

        if (this.mOrder > paramMemberColor.mOrder) {
            return 1;
        }
        if (this.mOrder < paramMemberColor.mOrder) {
            return 0;
        }
        return 0;
    }

    public String getColor() {
        return this.mColor;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public String getKey() {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = this.mValue;
        return String.format("color='%s'", arrayOfObject);
    }

    public Date getModified() {
        return this.mModified;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public String getValue() {
        return this.mValue;
    }

    public boolean isDefault() {
        return this.mIsDefault;
    }

    public void setColor(String paramString) {
        this.mColor = paramString;
    }

    public void setIndex(int paramInt) {
        this.mIndex = paramInt;
    }

    public void setIsDefault(boolean paramBoolean) {
        this.mIsDefault = paramBoolean;
    }

    public void setModified(Date paramDate) {
        this.mModified = paramDate;
    }

    public void setOrder(int paramInt) {
        this.mOrder = paramInt;
    }

    public void setValue(String paramString) {
        this.mValue = paramString;
    }

    public void updateFrom(BaseModel paramBaseModel) {
        if (!(paramBaseModel instanceof MemberColor))
            return;
        MemberColor localMemberColor = (MemberColor) paramBaseModel;
        this.mColor = localMemberColor.getColor();
        this.mValue = localMemberColor.getValue();
        this.mOrder = localMemberColor.getOrder();
        this.mModified = localMemberColor.getModified();
        this.mIsDefault = localMemberColor.checkIsDefault();
        this.mOwnerEmail = localMemberColor.mOwnerEmail;
    }
}