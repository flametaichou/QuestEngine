package ru.flametaichou.quest.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SceneDto extends DomainEntityDto {

    private String name;
    private String text;
    private UUID uniqueCode;
    private QuestFileDto backgroundFile;
    private QuestFileDto portraitFile;
    private DomainEntityDto quest;
    private String type;
    private Set<OptionDto> options = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(UUID uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public QuestFileDto getBackgroundFile() {
        return backgroundFile;
    }

    public void setBackgroundFile(QuestFileDto backgroundFile) {
        this.backgroundFile = backgroundFile;
    }

    public QuestFileDto getPortraitFile() {
        return portraitFile;
    }

    public void setPortraitFile(QuestFileDto portraitFile) {
        this.portraitFile = portraitFile;
    }

    public DomainEntityDto getQuest() {
        return quest;
    }

    public void setQuest(DomainEntityDto quest) {
        this.quest = quest;
    }

    public Set<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(Set<OptionDto> options) {
        this.options = options;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
