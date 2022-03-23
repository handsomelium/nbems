package com.ake.nbems.eaps.constant;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class FormatConstants {

    public static final SimpleDateFormat DEFAULT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat NOCHAR = new SimpleDateFormat("yyyyMMddHHmmss");

    public static final DateTimeFormatter NOCHAR_DATETIME = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
}