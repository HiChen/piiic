package com.gewara.piiic.models;


import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Footer
{
    public static final int INDEX_CUSTOMIZE_FOOTER = 3;
    public static final int INDEX_LEFT_FOOTER = 0;
    public static final int INDEX_NO_FOOTER = 1;
    public static final int INDEX_RIGHT_FOOTER = 2;
    public static final int SIZE_FOOTERS = 4;

    @SerializedName("image")
    private String mImage;

    @SerializedName("image_link")
    private String mLink;

    @SerializedName("modified")
    private Date mModified;

    @SerializedName("selected")
    private int mSelected;

    public void downloadFooterImage(Context paramContext)
    {
        if (!TextUtils.isEmpty(this.mImage));
//            FileDownloader.download(this.mImage, CacheUtil.getFooterDir(paramContext), null, null, null);
    }

    public String getImage()
    {
        return this.mImage;
    }

    public String getLink()
    {
        return this.mLink;
    }

    public Date getModified()
    {
        return this.mModified;
    }

    public int getSelected()
    {
        return this.mSelected;
    }

    public void loadFooterImage(Context paramContext, ImageView paramImageView)
    {
//        if (!TextUtils.isEmpty(this.mImage))
//            PicassoMagic.get().load(this.mImage).placeholder(2130837609).fit().centerCrop().into(paramImageView);
    }

    public void setImage(String paramString)
    {
        this.mImage = paramString;
    }

    public void setLink(String paramString)
    {
        this.mLink = paramString;
    }

    public void setModified(Date paramDate)
    {
        this.mModified = paramDate;
    }

    public void setSelected(int paramInt)
    {
        this.mSelected = paramInt;
    }
}