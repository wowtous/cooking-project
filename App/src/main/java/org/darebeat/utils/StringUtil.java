package org.darebeat.utils;

import java.lang.StringBuffer;

public class StringUtil {
    /**
     * 字符串数组转换成以sep分割的字符串
     * @param  String[] o  数组
     * @param  String   sep 分隔符
     * @return  以sep分割的字符串
     */
    public static String join( String[] o , String sep){
        StringBuffer sb = new StringBuffer();

        for(int i=0 , len=o.length ; i<len ; i++){
            sb.append(String.valueOf(o[i]));
            if(i<len-1) sb.append(sep);
        }

        return sb.toString();
    }

    /**
     * 判断一个数组是否为空
     * @param  Object o  数组
     * @return   Boolean
     */
    public static Boolean isEmptyArray(Object[] o){
        if(null == o || o.length == 0){
            return true;
        }
        return false;
    }
}
