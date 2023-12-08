package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static final String TIME_ZONE = "Asia/Ho_Chi_Minh";
    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd hh:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_VN = "dd-MM-yyy";

    public DateUtils() {
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (date == null) {
            return null;
        } else {
            sdf.applyPattern("yyyy-MM-dd");
            return sdf.format(date);
        }
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (date == null) {
            return null;
        } else {
            sdf.applyPattern(pattern);
            return sdf.format(date);
        }
    }

    public static String formatDateVN(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (date == null) {
            return null;
        } else {
            sdf.applyPattern("dd-MM-yyy");
            return sdf.format(date);
        }
    }

    public static String formatDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (date == null) {
            return null;
        } else {
            sdf.applyPattern("yyyy-MM-dd hh:mm:ss");
            return sdf.format(date);
        }
    }

    public static Date fromString(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (date == null) {
            return null;
        } else {
            try {
                sdf.applyPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                return sdf.parse(date);
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public static Date fromStringDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (date == null) {
            return null;
        } else {
            try {
                sdf.applyPattern("yyyy-MM-dd");
                return sdf.parse(date);
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public static String nextOrBeforeNumberDate(Date date, int numberDate, String formatDate) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (date == null) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            sdf.applyPattern(formatDate);
            calendar.setTime(date);
            calendar.add(5, numberDate);
            return sdf.format(calendar.getTime());
        }
    }
}
