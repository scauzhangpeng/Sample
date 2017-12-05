package com.xyz.util.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZP on 2017/11/16.
 */

public class SDCardInfo implements Parcelable {

    private String path;

    private boolean isRemovable;

    private String state;

    private long maxFileSize;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isRemovable() {
        return isRemovable;
    }

    public void setRemovable(boolean removable) {
        isRemovable = removable;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeByte(this.isRemovable ? (byte) 1 : (byte) 0);
        dest.writeString(this.state);
        dest.writeLong(this.maxFileSize);
    }

    public SDCardInfo() {
    }

    protected SDCardInfo(Parcel in) {
        this.path = in.readString();
        this.isRemovable = in.readByte() != 0;
        this.state = in.readString();
        this.maxFileSize = in.readLong();
    }

    public static final Creator<SDCardInfo> CREATOR = new Creator<SDCardInfo>() {
        @Override
        public SDCardInfo createFromParcel(Parcel source) {
            return new SDCardInfo(source);
        }

        @Override
        public SDCardInfo[] newArray(int size) {
            return new SDCardInfo[size];
        }
    };
}
