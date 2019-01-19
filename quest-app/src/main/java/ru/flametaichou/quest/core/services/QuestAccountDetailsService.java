package ru.flametaichou.quest.core.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flametaichou.quest.core.dao.AccountDao;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.AccountRole;

@Service("questAccountDetailsService")
public class QuestAccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountDao accountDao;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        Account account = accountDao.findByUserName(username);
        if (Objects.nonNull(account)) {
            List<GrantedAuthority> authorities =
                    buildUserAuthority(account.getAccountRoles());

            return buildUserForAuthentication(account, authorities);

        } else {

            return null;
        }
    }

    // Converts Account account to
    // org.springframework.security.core.userdetails.Account
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(Account account, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(), account.getPassword(), account.isEnabled(), true, true, true, authorities
        );
    }

    private List<GrantedAuthority> buildUserAuthority(Set<AccountRole> accountRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (AccountRole accountRole : accountRoles) {
            // Здесь происходит проверка роли
            setAuths.add(new SimpleGrantedAuthority(accountRole.getRole().name()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}