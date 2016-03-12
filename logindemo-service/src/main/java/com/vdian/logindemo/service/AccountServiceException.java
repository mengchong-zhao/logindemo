package com.vdian.logindemo.service;

/**
 * Created by chaofeng.zcf on 2016/3/12.
 */
public class AccountServiceException extends Exception {

    public AccountServiceException(String message) {
        super(message);
    }

    public AccountServiceException(String message, Throwable t){
        super(message, t);
    }
}
