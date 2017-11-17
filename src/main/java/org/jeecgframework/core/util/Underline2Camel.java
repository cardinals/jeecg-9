package org.jeecgframework.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 驼峰法-下划线互转
 * Created by 云强 on 2017/11/17.
 */
public class Underline2Camel {

    public static void main(String[] args) {
        String line = "springDateNow";
        System.out.println(humpToLine(line));//f_parent_no_leader
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰法转下划线
     *
     * @param str 源字符串
     * @return 转换后的字符串
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 将map的key从驼峰转为下划线风格
     *
     * @param map
     * @return
     */
    public static Map mapKeyHumpToLine(Map map) {
        Map temp = new HashMap();
        for (Object key : map.keySet()) {
            temp.put(humpToLine(key.toString()), map.get(key));
        }
        return temp;
    }
}

