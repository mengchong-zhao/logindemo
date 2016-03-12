package com.vdian.logindemo.persist;

/**
 * Created by chaofeng.zcf on 2016/3/10.
 */
public class AccountPersistException extends Exception {

    private static final long serialVersionUID = 8717240821101207504L;

    public AccountPersistException(String message){
        super(message);
    }

    public AccountPersistException(String message, Throwable t){
        super(message, t);
    }
}
