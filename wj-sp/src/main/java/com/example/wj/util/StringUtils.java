package com.example.wj.util;

import java.util.Random;

//生成指定长度的随机字符串
//在包含小写字母和数字的字符串中随机取出指定长度的字符组成字符串
public class StringUtils {
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
