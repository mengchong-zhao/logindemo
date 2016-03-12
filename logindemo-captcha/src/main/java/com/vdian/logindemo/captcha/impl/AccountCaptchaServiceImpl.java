package com.vdian.logindemo.captcha.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.vdian.logindemo.captcha.AccountCaptchaException;
import com.vdian.logindemo.captcha.AccountCaptchaService;
import com.vdian.logindemo.captcha.RandomGenerator;
import org.springframework.beans.factory.InitializingBean;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chaofeng.zcf on 2016/3/12.
 */
public class AccountCaptchaServiceImpl implements AccountCaptchaService, InitializingBean {
    private DefaultKaptcha producer;

    private Map<String, String> captchaMap = new HashMap<String, String>();

    private List<String> preDefinedTexts;

    private int textCount = 0;

    /**
     * SpringFramework的InitializingBean接口方法，在初始化对象的时候调用，一般都是放对类本身一些配置的初始化代码
     * @throws Exception
     */
    public void afterPropertiesSet()throws Exception{
        producer = new DefaultKaptcha();
        producer.setConfig(new Config(new Properties()));
    }

    public String generateCaptchaKey() throws AccountCaptchaException{
        String key = RandomGenerator.getRandomString();
        String value = getCaptchaText();
        captchaMap.put(key, value);
        return key;
    }

    /**
     * 获取生成验证图片的字符串
     * @return
     */
    private String getCaptchaText(){
        if(preDefinedTexts != null && !preDefinedTexts.isEmpty()){
            //这段的逻辑，主要是为了测试的变量，如果有注入的验证码字符串就是用默认的，否则用produce生成
            String text = preDefinedTexts.get(textCount);
            textCount = (textCount + 1) % preDefinedTexts.size();
            return text;
        }else {
            return producer.createText();
        }
    }

    public byte[] generateCaptchaImage(String captchaKey) throws AccountCaptchaException{
        //通过键值找到对应的验证码字符串
        String text = captchaMap.get(captchaKey);
        if(text == null){
            throw new AccountCaptchaException("Captch key" + captchaKey + "not found !");
        }
        BufferedImage image = producer.createImage(text);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //把生成的图片读成数据流
        try{
            ImageIO.write(image, "jpg", out);
        }catch (IOException e){
            throw new AccountCaptchaException("failed to write captcha stream", e);
        }
        return out.toByteArray();
    }

    /**
     * 根据用户返回的键值，从map中找到对应着value值，做校验
     * @param captchaKey
     * @param captchaValue
     * @return
     * @throws AccountCaptchaException
     */
    public boolean validateCaptcha(String captchaKey, String captchaValue) throws AccountCaptchaException{
        String text = captchaMap.get(captchaKey);
        if(text == null){
            throw new AccountCaptchaException("captch key" + captchaKey + "not found");
        }
        if(text.equals(captchaValue)){
            captchaMap.remove(captchaKey);
            return true;
        }else {
            return false;
        }
    }

    public List<String> getPreDefinedTexts(){
        return preDefinedTexts;
    }

    public void setPreDefinedTexts(List<String> preDefinedTexts){
        this.preDefinedTexts = preDefinedTexts;
    }
}
