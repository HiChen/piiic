package com.gewara.piiic.models;

/**
 * Created by user on 2016/1/26.
 */
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import nl.qbusict.cupboard.annotation.Column;

public class Ad extends BaseModel
        implements Comparable<Ad>
{
    public static final String C_AD_ID = "_ad_id";

    @SerializedName("id")
    @Column("_ad_id")
    private int mAdId;

    @SerializedName("display_seconds")
    @Column("_display_seconds")
    private int mDisplaySeconds;

    @SerializedName("link")
    @Column("_link")
    private String mLink;

    @SerializedName("medias")
    @Column("_medias")
    private String[] mMedias;

    @SerializedName("modified")
    @Column("_modified")
    private Date mModified;

    @SerializedName("start_display")
    @Column("_start_display")
    private Date mStartDisplay;

    @SerializedName("stop_display")
    @Column("_stop_display")
    private Date mStopDisplay;

    @SerializedName("type")
    @Column("_type")
    private String mType;

    @SerializedName("weight")
    @Column("_weight")
    private int mWeight;

    public int compareTo(Ad paramAd)
    {
        return this.mWeight - paramAd.mWeight;
    }

    public int getAdId()
    {
        return this.mAdId;
    }

    public int getDisplaySeconds()
    {
        return this.mDisplaySeconds;
    }

    public String getLink()
    {
        return this.mLink;
    }

    public String[] getMedias()
    {
        return this.mMedias;
    }

    public Date getModified()
    {
        return this.mModified;
    }

    public Date getStartDisplay()
    {
        return this.mStartDisplay;
    }

    public Date getStopDisplay()
    {
        return this.mStopDisplay;
    }

    public String getType()
    {
        return this.mType;
    }

    public int getWeight()
    {
        return this.mWeight;
    }

    public void setAdId(int paramInt)
    {
        this.mAdId = paramInt;
    }

    public void setDisplaySeconds(int paramInt)
    {
        this.mDisplaySeconds = paramInt;
    }

    public void setLink(String paramString)
    {
        this.mLink = paramString;
    }

    public void setMedias(String[] paramArrayOfString)
    {
        this.mMedias = paramArrayOfString;
    }

    public void setModified(Date paramDate)
    {
        this.mModified = paramDate;
    }

    public void setStartDisplay(Date paramDate)
    {
        this.mStartDisplay = paramDate;
    }

    public void setStopDisplay(Date paramDate)
    {
        this.mStopDisplay = paramDate;
    }

    public void setType(String paramString)
    {
        this.mType = paramString;
    }

    public void setWeight(int paramInt)
    {
        this.mWeight = paramInt;
    }

    public void updateFrom(BaseModel paramBaseModel)
    {
        if (!(paramBaseModel instanceof Ad))
            return;
        Ad localAd = (Ad)paramBaseModel;
        this.mAdId = localAd.mAdId;
        this.mModified = localAd.mModified;
        this.mStartDisplay = localAd.mStartDisplay;
        this.mStopDisplay = localAd.mStopDisplay;
        this.mType = localAd.mType;
        this.mLink = localAd.mLink;
        this.mMedias = localAd.mMedias;
        this.mWeight = localAd.mWeight;
        this.mDisplaySeconds = localAd.mDisplaySeconds;
    }
}
