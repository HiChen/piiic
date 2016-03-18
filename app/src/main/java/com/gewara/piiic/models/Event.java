package com.gewara.piiic.models;

/**
 * Created by user on 2016/1/26.
 */
import com.google.gson.annotations.SerializedName;
import nl.qbusict.cupboard.annotation.Column;

public class Event extends BaseModel
{
    public static final String C_EVENT_ID = "_event_id";

    @SerializedName("activity_date")
    @Column("_activity_date")
    private String mActivityDate;

    @SerializedName("author")
    @Column("_author")
    private String mAuthor;

    @SerializedName("city")
    @Column("_city")
    private String mCity;

    @SerializedName("created")
    @Column("_created")
    private String mCreated;

    @SerializedName("description")
    @Column("_description")
    private String mDescription;

    @SerializedName("id")
    @Column("_event_id")
    private int mEventId;

    @SerializedName("modified")
    @Column("_modified")
    private String mModified;

    @SerializedName("title")
    @Column("_title")
    private String mTitle;

    @SerializedName("html_url")
    @Column("_html_url")
    private String mUrl;

    public String getActivityDate()
    {
        return this.mActivityDate;
    }

    public String getAuthor()
    {
        return this.mAuthor;
    }

    public String getCity()
    {
        return this.mCity;
    }

    public String getCreated()
    {
        return this.mCreated;
    }

    public String getDescription()
    {
        return this.mDescription;
    }

    public int getEventId()
    {
        return this.mEventId;
    }

    public String getModified()
    {
        return this.mModified;
    }

    public String getTitle()
    {
        return this.mTitle;
    }

    public String getUrl()
    {
        return this.mUrl;
    }

    public void setActivityDate(String paramString)
    {
        this.mActivityDate = paramString;
    }

    public void setAuthor(String paramString)
    {
        this.mAuthor = paramString;
    }

    public void setCity(String paramString)
    {
        this.mCity = paramString;
    }

    public void setCreated(String paramString)
    {
        this.mCreated = paramString;
    }

    public void setDescription(String paramString)
    {
        this.mDescription = paramString;
    }

    public void setEventId(int paramInt)
    {
        this.mEventId = paramInt;
    }

    public void setModified(String paramString)
    {
        this.mModified = paramString;
    }

    public void setTitle(String paramString)
    {
        this.mTitle = paramString;
    }

    public void setUrl(String paramString)
    {
        this.mUrl = paramString;
    }

    public void updateFrom(BaseModel paramBaseModel)
    {
        if ((paramBaseModel == null) || (!(paramBaseModel instanceof Event)))
            return;
        Event localEvent = (Event)paramBaseModel;
        this.mEventId = localEvent.mEventId;
        this.mActivityDate = localEvent.mActivityDate;
        this.mCreated = localEvent.mCreated;
        this.mModified = localEvent.mModified;
        this.mTitle = localEvent.mTitle;
        this.mDescription = localEvent.mDescription;
        this.mCity = localEvent.mCity;
        this.mAuthor = localEvent.mAuthor;
        this.mUrl = localEvent.mUrl;
    }
}