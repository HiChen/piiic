package com.gewara.piiic.cropimage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class CropResult implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        public CropResult createFromParcel(Parcel paramAnonymousParcel) {
            return new CropResult(paramAnonymousParcel);
        }

        public CropResult[] newArray(int paramAnonymousInt) {
            return new CropResult[paramAnonymousInt];
        }
    };
    public static final String EXTRA_KEY = "CropResult";
    private final int mHeight;
    private final Uri mUri;
    private final int mWidth;

    public CropResult(Uri paramUri, int paramInt1, int paramInt2) {
        this.mUri = paramUri;
        this.mWidth = paramInt1;
        this.mHeight = paramInt2;
    }

    public CropResult(Parcel paramParcel) {
        this.mUri = ((Uri) paramParcel.readParcelable(CropResult.class.getClassLoader()));
        this.mWidth = paramParcel.readInt();
        this.mHeight = paramParcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt) {
        paramParcel.writeParcelable(this.mUri, 0);
        paramParcel.writeInt(this.mWidth);
        paramParcel.writeInt(this.mHeight);
    }
}