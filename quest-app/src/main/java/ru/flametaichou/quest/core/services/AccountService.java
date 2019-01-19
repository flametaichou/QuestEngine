package ru.flametaichou.quest.core.services;

import java.util.List;

import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.dto.AccountDto;

public interface AccountService {

    Account registerNewUserAccount(AccountDto accountDto) throws Exception ;
    List<AccountDto> listAccountDtos();
    void deleteUser(String username);
    void updateUser(Account account);
    void updateUser(AccountDto dto);
    Account findUserByName(String username);
    Account getCurrentUser();
    String getCurrentUserName();
    boolean isSuperuser(Account account);
    boolean hasPermissionsTo(Account account, Quest quest);

}
