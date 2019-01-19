package ru.flametaichou.quest.core.dto;

public class QuestFileDto extends DomainEntityDto {

    private String name;
    private String path;
    private EnumeratedDto type;
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

    public EnumeratedDto getType() {
        return type;
    }

    public void setType(EnumeratedDto type) {
        this.type = type;
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
}
