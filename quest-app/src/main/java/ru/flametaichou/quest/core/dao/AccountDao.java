package ru.flametaichou.quest.core.dao;

import java.util.List;

import ru.flametaichou.quest.core.domain.Account;

public interface AccountDao {

    void saveOrUpdate(Account account);
    Account findByUserName(String username);
    List<Account> listUsers();
    void delete(Account account);
}