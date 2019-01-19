package ru.flametaichou.quest.core.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.flametaichou.quest.core.dao.AccountRoleDao;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.AccountRole;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.dao.AccountDao;
import ru.flametaichou.quest.core.dto.AccountDto;
import ru.flametaichou.quest.core.dto.AccountRoleDto;

public class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountRoleDao accountRoleDao;

    private ConfigurableMapper mapper = new ConfigurableMapper();

    @Override
    @Transactional
    public Account registerNewUserAccount(AccountDto accountDto) throws Exception {
        if (loginExist(accountDto.getUsername())) {
            throw new Exception(
                    "There is an account with that login:" + accountDto.getUsername());
        }
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        account.setEnabled(true);

        accountDao.saveOrUpdate(account);

        // TODO: переделать этот ужас
        Account savedAccount = accountDao.findByUserName(account.getUsername());
        if (Objects.nonNull(savedAccount)) {
            Set<AccountRole> accountRoles = new HashSet<AccountRole>();
            if (Objects.isNull(accountDto.getUserRoles())) {
                accountDto.setUserRoles(new HashSet<AccountRoleDto>());
            }
            if (Objects.nonNull(accountDto.getUserRole())) {
                accountDto.getUserRoles().add(accountDto.getUserRole());
            }
            for (AccountRoleDto accountRoleDto : accountDto.getUserRoles()) {
                AccountRole accountRole = new AccountRole();
                accountRole.setRole(AccountRole.Role.valueOf(accountRoleDto.getRole()));
                accountRole.setAccount(savedAccount);
                accountRoleDao.saveOrUpdate(accountRole);
                accountRoles.add(accountRole);
            }
        }
        return savedAccount;
    }

    @Override
    @Transactional
    public List<AccountDto> listAccountDtos() {
        List<Account> accounts = accountDao.listUsers();
        List<AccountDto> dtos = new ArrayList<AccountDto>();
        for (Account account : accounts) {
            AccountDto dto = new AccountDto();
            dto.setId(account.getId());
            dto.setUsername(account.getUsername());
            dto.setUserRoles(new HashSet<AccountRoleDto>());
            for (AccountRole role : account.getAccountRoles()) {
                AccountRoleDto roleDto = new AccountRoleDto();
                roleDto.setRole(role.getRole().name());
                //уходим в рекурсию?
                //roleDto.setAccount(dto);
                dto.getUserRoles().add(roleDto);
            }
            if (!dto.getUserRoles().isEmpty()) {
                dto.setUserRole(dto.getUserRoles().iterator().next());
            }
            dto.setEnabled(account.isEnabled());
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        Account account = accountDao.findByUserName(username);
        boolean logial = false;
        if (logial) {
            account.setEnabled(false);
            accountDao.saveOrUpdate(account);
        } else {
            Set<AccountRole> roles = account.getAccountRoles();
            account.setAccountRoles(new HashSet<AccountRole>());
            accountDao.saveOrUpdate(account);
            for (AccountRole role : roles) {
                accountRoleDao.delete(role);
            }
            accountDao.delete(account);
        }
        //accountDao.delete(account);
    }

    @Override
    @Transactional
    public void updateUser(Account account) {
        accountDao.saveOrUpdate(account);
    }

    @Override
    @Transactional
    public void updateUser(AccountDto dto) {
        Account account = accountDao.findByUserName(dto.getUsername());
        if (Objects.isNull(account)) {
            logger.error("Could not fing account {}!", dto.getUsername());
            return;
        }

        accountDao.saveOrUpdate(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account findUserByName(String username) {
        return accountDao.findByUserName(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getCurrentUser() {
        String userName = getCurrentUserName();
        if (Objects.nonNull(userName)) {
            return findUserByName(userName);
        }
        return null;
    }

    private boolean loginExist(String username) {
        Account account = accountDao.findByUserName(username);
        if (Objects.nonNull(account)) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isSuperuser(Account account) {
        for (AccountRole ur : account.getAccountRoles()) {
            if (ur.getRole() == AccountRole.Role.ADMIN) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasPermissionsTo(Account account, Quest quest) {
        if (isSuperuser(account) || quest.getAccount().getId() == account.getId()) {
            return true;
        } else {
            return false;
        }
    }
}
