package com.gewara.piiic.models;

/**
 * Created by user on 2016/2/23.
 */
public class TextImage extends BaseModel {
    private String textId;
    private String textContent;
    private float textSize;
    private int textColor;
    private int  imageType;
    private String filePath;
    private float x;
    private float y;
    private int width;
    private int height;
    private float top;
    private float left;

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public int getImageType() {
        return imageType;
    }
    public void setTextId(String textId) {
        this.textId = textId;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getTextContent() {
        return textContent;
    }


    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }


    public float getTextSize() {
        return textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextColor() {
        return textColor;
    }


    public void setLeft(float left) {
        this.left = left;
    }

    public float getLeft() {
        return left;
    }

    public void setTop(float top) {
        this.top = top;
    }


    public float getTop() {
        return top;
    }

    @Override
    public void updateFrom(BaseModel paramBaseModel) {

    }
}
