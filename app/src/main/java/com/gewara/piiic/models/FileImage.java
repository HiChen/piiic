package com.gewara.piiic.models;

/**
 * Created by user on 2016/2/23.
 */
public class FileImage extends BaseModel {
    private String fileId;


    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    @Override
    public void updateFrom(BaseModel paramBaseModel) {

    }
}
