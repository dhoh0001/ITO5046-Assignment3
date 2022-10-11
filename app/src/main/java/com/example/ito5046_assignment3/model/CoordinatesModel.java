package com.example.ito5046_assignment3.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CoordinatesModel implements Parcelable {
    public double longitude;
    public double lattitude;
    public CoordinatesModel(Parcel in) {
        this.longitude = in.readDouble();
        this.lattitude = in.readDouble();
    }

    public CoordinatesModel() {
        this.lattitude = 0;
        this.longitude = 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<CoordinatesModel> CREATOR = new Parcelable.Creator<CoordinatesModel>() {
        @Override
        public CoordinatesModel createFromParcel(Parcel in) {
            return new CoordinatesModel(in);
        }

        @Override
        public CoordinatesModel[] newArray(int size) {
            return new CoordinatesModel[size];
        }
    };
}
