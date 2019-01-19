package ru.flametaichou.quest.core.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "QUEST_FILE")
@AttributeOverride(name = "id", column = @Column(name = "QUEST_FILE_ID"))
public class QuestFile extends DomainEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PATH", nullable = false)
    private String path;

    @Column(name = "TYPE", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private QuestFileType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUEST_ID", nullable = false)
    private Quest quest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public QuestFileType getType() {
        return type;
    }

    public void setType(QuestFileType type) {
        this.type = type;
    }

    public enum QuestFileType {
        PICTURE
    }
}
