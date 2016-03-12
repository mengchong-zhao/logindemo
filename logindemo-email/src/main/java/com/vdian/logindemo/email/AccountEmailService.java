package com.vdian.logindemo.email;

/**
 * Created by chaofeng.zcf on 2016/3/10.
 */
public interface AccountEmailService {
    void sendMail(String to, String subject, String htmlText) throws AccountEmailException;
}
