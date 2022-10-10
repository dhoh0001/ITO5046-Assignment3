package com.example.ito5046_assignment3.retrofit;

import com.google.gson.annotations.SerializedName;

public class response {
    //No need to map all keys, only those the fields you need
    @SerializedName("pos1")
    public String pos1;
    @SerializedName("pos2")
    public String pos2;
    @SerializedName("pos3")
    public String pos3;
    @SerializedName("pos1val")
    public int pos1val;
    @SerializedName("pos2val")
    public int pos2val;
    @SerializedName("pos3val")
    public int pos3val;

    public response(String pos1, String pos2, String pos3, int pos1val, int pos2val, int pos3val) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.pos3 = pos3;
        this.pos1val = pos1val;
        this.pos2val = pos2val;
        this.pos3val = pos3val;
    }

    public String getPos1() {
        return pos1;
    }

    public void setPos1(String pos1) {
        this.pos1 = pos1;
    }

    public String getPos2() {
        return pos2;
    }

    public void setPos2(String pos2) {
        this.pos2 = pos2;
    }

    public String getPos3() {
        return pos3;
    }

    public void setPos3(String pos3) {
        this.pos3 = pos3;
    }

    public int getPos1val() {
        return pos1val;
    }

    public void setPos1val(int pos1val) {
        this.pos1val = pos1val;
    }

    public int getPos2val() {
        return pos2val;
    }

    public void setPos2val(int pos2val) {
        this.pos2val = pos2val;
    }

    public int getPos3val() {
        return pos3val;
    }

    public void setPos3val(int pos3val) {
        this.pos3val = pos3val;
    }
}
