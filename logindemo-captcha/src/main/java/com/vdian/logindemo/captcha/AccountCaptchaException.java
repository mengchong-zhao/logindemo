package com.vdian.logindemo.captcha;

/**
 * Created by chaofeng.zcf on 2016/3/11.
 */
public class AccountCaptchaException extends Exception {

    private static final long serialVersionUID = -4308449620589552381L;

    public AccountCaptchaException(String message){
        super(message);
    }

    public AccountCaptchaException(String message, Throwable t){
        super(message, t);
    }
}
