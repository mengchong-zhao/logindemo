package com.vdian.logindemo.captcha;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;

/**
 * Created by chaofeng.zcf on 2016/3/12.
 */
public class RandomGeneratorTest {
    @Test
    public void testGetRandomString() throws Exception{
        Set<String> randoms = new HashSet<String>(100);
        for(int i=0; i< 100; i++){
            String random = RandomGenerator.getRandomString();
            //对随机生成器的检测，看每次生成的字符串是否相同
            assertFalse(randoms.contains(random));
            randoms.add(random);
        }
    }
}
