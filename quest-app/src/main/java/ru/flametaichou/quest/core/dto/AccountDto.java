package ru.flametaichou.quest.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto extends DomainEntityDto {

    private String username;

    private String password;

    private boolean enabled;

    private Set<AccountRoleDto> accountRoles = new HashSet<AccountRoleDto>(0);

    private AccountRoleDto accountRole;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<AccountRoleDto> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(Set<AccountRoleDto> accountRoles) {
        this.accountRoles = accountRoles;
    }

    public AccountRoleDto getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(AccountRoleDto accountRole) {
        this.accountRole = accountRole;
    }
}
