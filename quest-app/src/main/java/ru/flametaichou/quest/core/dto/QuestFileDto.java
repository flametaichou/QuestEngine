package ru.flametaichou.quest.core.dto;

import java.util.UUID;

public class QuestFileDto extends DomainEntityDto {

    private String name;
    private UUID uniqueCode;
    private String path;
    private String type;
    private DomainEntityDto account;
    private DomainEntityDto quest;

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

    public DomainEntityDto getAccount() {
        return account;
    }

    public void setAccount(DomainEntityDto account) {
        this.account = account;
    }

    public DomainEntityDto getQuest() {
        return quest;
    }

    public void setQuest(DomainEntityDto quest) {
        this.quest = quest;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(UUID uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}
