package com.vdian.logindemo.service;

/**
 * Created by chaofeng.zcf on 2016/3/12.
 */
public interface AccountService {
    /**
     * 生成验证码
     * @return
     * @throws AccountServiceException
     */
    String generateCaptchaKey()throws AccountServiceException;

    /**
     * 生成验证图片
     * @param captchaKey
     * @return
     * @throws AccountServiceException
     */
    byte[] generateCaptchaImage(String captchaKey)throws AccountServiceException;

    /**
     * 注册
     * @param signUpRequest
     * @throws AccountServiceException
     */
    void signUp(SignUpRequest signUpRequest)throws AccountServiceException;

    /**
     * 激活
     * @param activationNumber
     * @throws AccountServiceException
     */
    void activate(String activationNumber)throws AccountServiceException;

    /**
     * 登陆
     * @param id
     * @param passWord
     * @throws AccountServiceException
     */
    void login(String id, String passWord)throws AccountServiceException;
}
