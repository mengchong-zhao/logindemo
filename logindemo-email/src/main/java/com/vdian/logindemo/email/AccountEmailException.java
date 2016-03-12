package com.vdian.logindemo.email;

/**
 * Created by chaofeng.zcf on 2016/3/10.
 */
public class AccountEmailException extends Exception {

    private static final long serialVersionUID = 33147916463980255L;

    public AccountEmailException(String message){
        super(message);
    }

    public AccountEmailException(String message, Throwable throwable){
        super(message, throwable);
    }
}
