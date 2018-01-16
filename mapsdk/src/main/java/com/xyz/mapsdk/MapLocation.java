package com.xyz.mapsdk;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZP on 2018/1/15.
 */

public class MapLocation implements Parcelable, Cloneable {

    private double latitude;
    private double longitude;
    private String province;
    private String city;
    private String district;

    public MapLocation() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    @Override
    public String toString() {
        return "MapLocation{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.district);
    }

    protected MapLocation(Parcel in) {
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.province = in.readString();
        this.city = in.readString();
        this.district = in.readString();
    }

    public static final Creator<MapLocation> CREATOR = new Creator<MapLocation>() {
        @Override
        public MapLocation createFromParcel(Parcel source) {
            return new MapLocation(source);
        }

        @Override
        public MapLocation[] newArray(int size) {
            return new MapLocation[size];
        }
    };
}
