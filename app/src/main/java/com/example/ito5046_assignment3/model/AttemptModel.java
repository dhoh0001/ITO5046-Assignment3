package com.example.ito5046_assignment3.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AttemptModel implements Parcelable {
    public int id;
    public AttemptModel(Parcel in) {
        this.id = in.readInt();
    }

    public AttemptModel() {
        this.id = -1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return this.id;
    }

    public static final Creator<AttemptModel> CREATOR = new Creator<AttemptModel>() {
        @Override
        public AttemptModel createFromParcel(Parcel in) {
            return new AttemptModel(in);
        }

        @Override
        public AttemptModel[] newArray(int size) {
            return new AttemptModel[size];
        }
    };
}
