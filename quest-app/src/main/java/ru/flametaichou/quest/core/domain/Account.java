package ru.flametaichou.quest.core.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
@AttributeOverride(name = "id", column = @Column(name = "ACCOUNT_ID"))
public class Account extends DomainEntity {

    @Column(name = "USERNAME", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 60)
    private String password;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    private Set<AccountRole> accountRoles = new HashSet<AccountRole>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<Quest> quests = new HashSet<>();

    public Account() {
    }

    public Account(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public Account(String username, String password,
                   boolean enabled, Set<AccountRole> accountRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountRoles = accountRole;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<AccountRole> getAccountRoles() {
        return this.accountRoles;
    }

    public void setAccountRoles(Set<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }

    public Set<Quest> getQuests() {
        return quests;
    }

    public void setQuests(Set<Quest> quests) {
        this.quests = quests;
    }
}