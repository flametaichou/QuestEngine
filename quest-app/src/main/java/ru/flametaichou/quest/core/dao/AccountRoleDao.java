package ru.flametaichou.quest.core.dao;

import ru.flametaichou.quest.core.domain.AccountRole;

public interface AccountRoleDao {
    void saveOrUpdate(AccountRole accountRole);
    void delete(AccountRole accountRole);
    void update(AccountRole accountRole);
}
