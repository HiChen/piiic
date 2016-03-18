package com.gewara.piiic.models;

import java.util.List;

/**
 * Created by user on 2016/2/23.
 */
public class IMageArticle extends BaseModel {

    private String bitmapId;
    private int bitmapWidth;
    private int bitmapHeight;
    private List<TextImage> textImages;


    public void setBitmapId(String bitmapId) {
        this.bitmapId = bitmapId;
    }

    public String getBitmapId() {
        return bitmapId;
    }


    public void setBitmapHeight(int bitmapHeight) {
        this.bitmapHeight = bitmapHeight;
    }

    public int getBitmapHeight() {
        return bitmapHeight;
    }

    public void setBitmapWidth(int bitmapWidth) {
        this.bitmapWidth = bitmapWidth;
    }

    public int getBitmapWidth() {
        return bitmapWidth;
    }

    public void setTextImages(List<TextImage> textImages) {
        this.textImages = textImages;
    }


    public List<TextImage> getTextImages() {
        return textImages;
    }


    @Override
    public void updateFrom(BaseModel paramBaseModel) {

    }
}
