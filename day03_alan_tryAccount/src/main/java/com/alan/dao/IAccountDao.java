package com.alan.dao;

import com.alan.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();
    /**
     * 查询一个
     * @return
     */
    Account findAccountById(Integer accountId);
    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);
    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);
    /**
     *
     * @param accountId
     */
    void deleteAccount(Integer accountId);

    /**
     * 根据名称查询账户
     * @param accountName
     * @return 有唯一结果就返回，没有结果就返回null
     */
    Account findAccountByName(String accountName);
}
