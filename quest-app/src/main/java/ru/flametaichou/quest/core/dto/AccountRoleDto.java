package ru.flametaichou.quest.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRoleDto extends DomainEntityDto {

    private AccountDto user;

    private String role;

    public AccountDto getUser() {
        return user;
    }

    public void setUser(AccountDto user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
