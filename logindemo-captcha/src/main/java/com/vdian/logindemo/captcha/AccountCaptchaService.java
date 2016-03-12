package com.vdian.logindemo.captcha;

import java.util.List;

/**
 * Created by chaofeng.zcf on 2016/3/11.
 */
public interface AccountCaptchaService {

    /**
     * 用来生成随机的主键
     * @return
     * @throws AccountCaptchaException
     */
    String generateCaptchaKey() throws AccountCaptchaException;

    /**
     * 用来生成验证图片
     * @param captchaKey
     * @return
     * @throws AccountCaptchaException
     */
    byte[] generateCaptchaImage(String captchaKey) throws AccountCaptchaException;

    /**
     * 用来验证用户反馈的主键和值
     * @param captchaKey
     * @param captchaValue
     * @return
     * @throws AccountCaptchaException
     */
    boolean validateCaptcha(String captchaKey, String captchaValue) throws AccountCaptchaException;

    /**
     * 用于性能测试，人为设置主键，对生成的验证图片进行判断
     * @return
     */
    List<String> getPreDefinedTexts();

    void setPreDefinedTexts(List<String> preDefinedTexts);
}
