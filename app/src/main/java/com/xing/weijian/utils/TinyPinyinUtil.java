package com.xing.weijian.utils;

import android.util.Log;

import com.ecity.android.tinypinyin.Pinyin;

/**
 * Created by Administrator on 2017/5/14.
 */

public class TinyPinyinUtil {

    private TinyPinyinUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String toPinyin(char c) {
        if (Pinyin.isChinese(c)) {
            Log.i("debug", "TinyPinyinUtil.toPinyin() = " + Pinyin.toPinyin(c));
            return Pinyin.toPinyin(c);
        } else {
            Log.i("debug", "TinyPinyinUtil.toPinyin() = " + String.valueOf(c));
            return String.valueOf(c);
        }

    }


    public static String toPinyin(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Pinyin.isChinese(c)) {
                String chars = Pinyin.toPinyin(c);
                sb.append(chars);
            } else {
                sb.append(c);
            }
        }
        return sb.toString().toLowerCase();
    }


}
