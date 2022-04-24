package com.example.goodplanet.utils;

public class StringUtils {
    private static StringUtils stringUtils = null;
    static {
        if (stringUtils == null) {
            stringUtils = new StringUtils();
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param inStr
     * @return
     */
    public static boolean isEmpty(String inStr) {
        if (inStr == null || "".equals(inStr)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为空
     *
     * @param text
     * @return
     */
    public static boolean isNullOrEmpty(String text) {
        if (text == null || "".equals(text.trim()) || text.trim().length() == 0
                || "null".equals(text.trim())) {
            return true;
        } else {
            return false;
        }
    }

}
