package com.vdian.logindemo.persist;

/**
 * Created by chaofeng.zcf on 2016/3/10.
 */
public interface AccountPersistService {
    /**
     * add
     * @param account
     * @return
     * @throws AccountPersistException
     */
    Account createAccount(Account account) throws AccountPersistException;

    /**
     *
     * @param id
     * @return
     * @throws AccountPersistException
     */
    Account readAccount(String id) throws AccountPersistException;

    /**
     *
     * @param id
     * @throws AccountPersistException
     */
    void deleteAccount(String id) throws AccountPersistException;

    /**
     *
     * @param account
     * @return
     * @throws AccountPersistException
     */
    Account updateAccount(Account account) throws AccountPersistException;

}
