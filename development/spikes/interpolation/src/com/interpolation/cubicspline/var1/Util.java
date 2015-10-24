package com.interpolation.cubicspline.var1;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class defines various utility functions used in Rrd4j.
 *
 * @author Sasa Markovic
 */
public class Util {

    /** Constant <code>MAX_LONG=Long.MAX_VALUE</code> */
    public static final long MAX_LONG = Long.MAX_VALUE;
    /** Constant <code>MIN_LONG=-Long.MAX_VALUE</code> */
    public static final long MIN_LONG = -Long.MAX_VALUE;

    /** Constant <code>MAX_DOUBLE=Double.MAX_VALUE</code> */
    public static final double MAX_DOUBLE = Double.MAX_VALUE;
    /** Constant <code>MIN_DOUBLE=-Double.MAX_VALUE</code> */
    public static final double MIN_DOUBLE = -Double.MAX_VALUE;

    // pattern RRDTool uses to format doubles in XML files
    static final String PATTERN = "0.0000000000E00";
    // directory under $USER_HOME used for demo graphs storing
    static final String RRD4J_DIR = "rrd4j-demo";

    static final DecimalFormat df;

    static {
        df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
        df.applyPattern(PATTERN);
        df.setPositivePrefix("+");
    }

    private Util() {

    }

    /**
     * Returns current timestamp in seconds (without milliseconds). Returned timestamp
     * is obtained with the following expression:
     * <p>
     * <code>(System.currentTimeMillis() + 500L) / 1000L</code>
     *
     * @return Current timestamp
     */
    public static long getTime() {
        return (System.currentTimeMillis() + 500L) / 1000L;
    }

    /**
     * Just an alias for {@link #getTime()} method.
     *
     * @return Current timestamp (without milliseconds)
     */
    public static long getTimestamp() {
        return getTime();
    }

    /**
     * Rounds the given timestamp to the nearest whole &quot;step&quot;. Rounded value is obtained
     * from the following expression:
     * <p>
     * <code>timestamp - timestamp % step;</code>
     *
     * @param timestamp Timestamp in seconds
     * @param step      Step in seconds
     * @return "Rounded" timestamp
     */
    public static long normalize(long timestamp, long step) {
        return timestamp - timestamp % step;
    }



    /**
     * Returns <code>Date</code> object for the given timestamp (in seconds, without
     * milliseconds)
     *
     * @param timestamp Timestamp in seconds.
     * @return Corresponding Date object.
     */
    public static Date getDate(long timestamp) {
        return new Date(timestamp * 1000L);
    }

    /**
     * Returns <code>Calendar</code> object for the given timestamp
     * (in seconds, without milliseconds)
     *
     * @param timestamp Timestamp in seconds.
     * @return Corresponding Calendar object.
     */
    public static Calendar getCalendar(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000L);
        return calendar;
    }

    /**
     * Returns <code>Calendar</code> object for the given Date object
     *
     * @param date Date object
     * @return Corresponding Calendar object.
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Returns timestamp (unix epoch) for the given Date object
     *
     * @param date Date object
     * @return Corresponding timestamp (without milliseconds)
     */
    public static long getTimestamp(Date date) {
        // round to whole seconds, ignore milliseconds
        return (date.getTime() + 499L) / 1000L;
    }

    /**
     * Returns timestamp (unix epoch) for the given Calendar object
     *
     * @param gc Calendar object
     * @return Corresponding timestamp (without milliseconds)
     */
    public static long getTimestamp(Calendar gc) {
        return getTimestamp(gc.getTime());
    }

    /**
     * Returns timestamp (unix epoch) for the given year, month, day, hour and minute.
     *
     * @param year  Year
     * @param month Month (zero-based)
     * @param day   Day in month
     * @param hour  Hour
     * @param min   Minute
     * @return Corresponding timestamp
     */
    public static long getTimestamp(int year, int month, int day, int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day, hour, min);
        return Util.getTimestamp(calendar);
    }

    /**
     * Returns timestamp (unix epoch) for the given year, month and day.
     *
     * @param year  Year
     * @param month Month (zero-based)
     * @param day   Day in month
     * @return Corresponding timestamp
     */
    public static long getTimestamp(int year, int month, int day) {
        return Util.getTimestamp(year, month, day, 0, 0);
    }




    static final String ISO_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";   // ISO

    /**
     * Creates Calendar object from a string. The string should represent
     * either a long integer (UNIX timestamp in seconds without milliseconds,
     * like "1002354657") or a human readable date string in the format "yyyy-MM-dd HH:mm:ss"
     * (like "2004-02-25 12:23:45").
     *
     * @param timeStr Input string
     * @return Calendar object
     */
    public static Calendar getCalendar(String timeStr) {
        // try to parse it as long
        try {
            long timestamp = Long.parseLong(timeStr);
            return Util.getCalendar(timestamp);
        }
        catch (NumberFormatException e) {
        }
        // not a long timestamp, try to parse it as data
        SimpleDateFormat df = new SimpleDateFormat(ISO_DATE_FORMAT);
        df.setLenient(false);
        try {
            Date date = df.parse(timeStr);
            return Util.getCalendar(date);
        }
        catch (ParseException e) {
            throw new IllegalArgumentException("Time/date not in " + ISO_DATE_FORMAT +
                    " format: " + timeStr);
        }
    }


}