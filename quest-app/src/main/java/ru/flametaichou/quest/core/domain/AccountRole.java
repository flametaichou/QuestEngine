package ru.flametaichou.quest.core.domain;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_ROLE")
@AttributeOverride(name = "id", column = @Column(name = "ACCOUNT_ROLE_ID"))
public class AccountRole extends DomainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @Column(name = "ROLE", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private Role role;

    public AccountRole() {
    }

    public AccountRole(Account account, Role role) {
        this.account = account;
        this.role = role;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {
        ADMIN,
        CREATOR,
        USER
    }

}
