package com.gewara.piiic.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChangeUsername {

    @SerializedName("username")
    private String mUsername;

    public String getUsername() {
        return this.mUsername;
    }

    public void setUsername(String paramString) {
        this.mUsername = paramString;
    }

    public static final class AvatarError {

        @SerializedName("avatar")
        private List<String> mErrors;

        public List<String> getErrors() {
            return this.mErrors;
        }

        public void setErrors(List<String> paramList) {
            this.mErrors = paramList;
        }
    }

    public static final class Error {

        @SerializedName("username")
        private List<String> mErrors;

        public List<String> getErrors() {
            return this.mErrors;
        }

        public void setErrors(List<String> paramList) {
            this.mErrors = paramList;
        }
    }

    public static final class Response {

        @SerializedName("avatar")
        private String mAvatar;

        @SerializedName("email")
        private String mEmail;

        @SerializedName("id")
        private int mId;

        @SerializedName("username")
        private String mUsername;

        public String getAvatar() {
            return this.mAvatar;
        }

        public String getEmail() {
            return this.mEmail;
        }

        public int getId() {
            return this.mId;
        }

        public String getUsername() {
            return this.mUsername;
        }

        public void setAvatar(String paramString) {
            this.mAvatar = paramString;
        }

        public void setEmail(String paramString) {
            this.mEmail = paramString;
        }

        public void setId(int paramInt) {
            this.mId = paramInt;
        }

        public void setUsername(String paramString) {
            this.mUsername = paramString;
        }
    }
}