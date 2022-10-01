package com.example.ito5046_assignment3.model;

import java.util.ArrayList;
import java.util.List;

public class CourseResult {
    private String mUnit;
    private int mMark;
    public CourseResult(String unit, int mark) {
        mUnit = unit;
        mMark = mark;
    }
    public String getUnit() {
        return mUnit;
    }
    public int getMark() {
        return mMark;
    }
    //this is used to populate the list with a few items at the start of the application
//it is static so it can be called without instantiating the class
    public static List<CourseResult> createContactsList() {
        List<CourseResult> units = new ArrayList<CourseResult>();
        units.add(new CourseResult( "FIT5046",87));
        units.add(new CourseResult( "FIT5152",77));
        return units;
    }
}
