package com.gewara.piiic.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import nl.qbusict.cupboard.annotation.Column;

/**
 * Created by user on 2016/1/25.
 */
public abstract class BaseModel implements Serializable {
    public static final String C_ID = "_id";
    public static final String C_OWNER_EMAIL = "_owner_email";
    public static final String C_SYNC = "_is_sync";
    public static final int SYNC = 1;
    public static final int UNSYNC = 2;

    @SerializedName("__id")
    @Column("_id")
    protected Long mId;

    @SerializedName("__is_sync")
    @Column("_is_sync")
    protected int mIsSync;

    @SerializedName("__owner_email")
    @Column("_owner_email")
    protected String mOwnerEmail = "515928615@qq.com";
        //AccessTokenPreferences.getEmail();

    public boolean equals(Object paramObject)
    {
        if (this == paramObject);
        BaseModel  localBaseModel = (BaseModel)paramObject;

            if ((paramObject == null) || (getClass() != paramObject.getClass()))
                return false;

            if (this.mId == null) {
                return false;
            }
            if (localBaseModel.mId == null) {
                return false;
            }
            if (this.mId.equals(localBaseModel.mId)){
                return true;
            }
        return  false;
    }

    public Long getId()
    {
        return this.mId;
    }

    public String getOwnerEmail()
    {
        return this.mOwnerEmail;
    }

    public int hashCode()
    {
        if (this.mId != null)
            return this.mId.hashCode();
        return 0;
    }

    public boolean isSync()
    {
        return this.mIsSync > 0;
    }

    public void setId(Long paramLong)
    {
        this.mId = paramLong;
    }

    public void setOwnerEmail(String paramString)
    {
    }

    public void setSync(int paramInt)
    {
        this.mIsSync = paramInt;
    }

    public abstract void updateFrom(BaseModel paramBaseModel);
}
