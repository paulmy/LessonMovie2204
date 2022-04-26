package ru.myitschool.lessonmovie.utils;

import android.content.res.Resources;

import ru.myitschool.lessonmovie.R;

public class StringFormat {
    public static String formatTime(Resources res, int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        if (h > 0 && m > 0)
            return String.format(res.getString(R.string.time_h) + " " + res.getString(R.string.time_m),h,m);
        else if (h > 0) return String.format(res.getString(R.string.time_h),h);
        else return String.format(res.getString(R.string.time_m),m);
    }
}
