package com.tuoyi.threebusinesscity.url;

/**
 * Created by md
 * on 2018/4/23 0023.
 */

public class Config {
   /* public static String S = "http://sszl.tuoee.com/";
    public static String IMGS = S +"public/uploads/";*/

    public static StringBuffer stringBuffer1=new StringBuffer("http://sszl.tuoee.com/");
    public static String s=stringBuffer1.toString();
    public static StringBuffer stringBuffer2=stringBuffer1.append("public/uploads/");
    public static String IMGS=stringBuffer2.toString();
}
