package com.gewara.piiic.models;

/**
 * Created by user on 2016/1/26.
 */

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Account
{

    @SerializedName("avatar")
    private String mAvatar;

    @SerializedName("birthday")
    private String mBirthday;

    @SerializedName("city")
    private List<String> mCity;

    @SerializedName("city_key")
    private List<String> mCityKey;

    @SerializedName("coin")
    private int mCoin;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("gender")
    private boolean mGender;

    @SerializedName("habits")
    private List<List<String>> mHabits;

    @SerializedName("habits_key")
    private List<List<String>> mHabitsKey;

    @SerializedName("id")
    private int mId;

    @SerializedName("register_date")
    private Date mRegisterDate;

//    @SerializedName("role")
//    private Role mRole;

    @SerializedName("username")
    private String mUsername;

    @SerializedName("verify_code")
    private String mVerifyCode;

    public void downloadAvatar()
    {
//        if (!TextUtils.isEmpty(this.mAvatar))
//            FileDownloader.download(this.mAvatar, CacheUtil.getAvatarDir(PiiicApplication.getApplication()), null, null, null);
    }

    public String getAvatar()
    {
        return this.mAvatar;
    }

    public String getBirthday()
    {
        return this.mBirthday;
    }

    public List<String> getCity()
    {
        return this.mCity;
    }

    public List<String> getCityKey()
    {
        return this.mCityKey;
    }

    public int getCoin()
    {
        return this.mCoin;
    }

    public String getDescription()
    {
        return this.mDescription;
    }

    public String getEmail()
    {
        return this.mEmail;
    }

    public List<List<String>> getHabits()
    {
        return this.mHabits;
    }

    public List<List<String>> getHabitsKey()
    {
        return this.mHabitsKey;
    }

    public int getId()
    {
        return this.mId;
    }

    public Date getRegisterDate()
    {
        return this.mRegisterDate;
    }

//    public Role getRole()
//    {
//        if (this.mRole == null)
//            return Role.USER;
//        return this.mRole;
//    }

    public String getUsername()
    {
        if (this.mUsername == null)
            return "";
        return this.mUsername;
    }

    public String getVerifyCode()
    {
        return this.mVerifyCode;
    }

    public boolean isGender()
    {
        return this.mGender;
    }

    public void setAvatar(String paramString)
    {
        this.mAvatar = paramString;
    }

    public void setBirthday(String paramString)
    {
        this.mBirthday = paramString;
    }

    public void setCity(List<String> paramList)
    {
        this.mCity = paramList;
    }

    public void setCityKey(List<String> paramList)
    {
        this.mCityKey = paramList;
    }

    public void setCoin(int paramInt)
    {
        this.mCoin = paramInt;
    }

    public void setDescription(String paramString)
    {
        this.mDescription = paramString;
    }

    public void setEmail(String paramString)
    {
        this.mEmail = paramString;
    }

    public void setGender(boolean paramBoolean)
    {
        this.mGender = paramBoolean;
    }

    public void setHabits(List<List<String>> paramList)
    {
        this.mHabits = paramList;
    }

    public void setHabitsKey(List<List<String>> paramList)
    {
        this.mHabitsKey = paramList;
    }

    public void setId(int paramInt)
    {
        this.mId = paramInt;
    }

    public void setRegisterDate(Date paramDate)
    {
        this.mRegisterDate = paramDate;
    }

//    public void setRole(Role paramRole)
//    {
//        this.mRole = paramRole;
//    }

    public void setUsername(String paramString)
    {
        this.mUsername = paramString;
    }

    public void setVerifyCode(String paramString)
    {
        this.mVerifyCode = paramString;
    }
}
