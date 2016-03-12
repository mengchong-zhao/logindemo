package com.vdian.logindemo.captcha;

import java.util.Random;

/**
 * Created by chaofeng.zcf on 2016/3/12.
 */
public class RandomGenerator {
    private static String range = "0123456789abcdefghijklmnopqistuvwxyz";

    /**
     * 线程安全的，生成随机主键，特别注意这里生成的主键不是用于生成验证图片中的字符，而是验证码字符所对应的键值
     * @return
     */
    public static synchronized String getRandomString(){
        Random random = new Random();
        StringBuffer result = new StringBuffer();
        for(int i=0; i< 8; i++){
            result.append(range.charAt(random.nextInt(range.length())));
        }
        return result.toString();
    }

}
