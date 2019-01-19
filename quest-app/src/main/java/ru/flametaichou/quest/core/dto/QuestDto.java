package ru.flametaichou.quest.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.QuestFile;
import ru.flametaichou.quest.core.domain.Scene;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestDto extends DomainEntityDto {

    private String name;
    private String description;
    private String uniqueCode;
    private DomainEntityDto account;
    private DomainEntityDto firstScene;
    private Set<DomainEntityDto> scenes = new HashSet<>();
    private Set<DomainEntityDto> files = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DomainEntityDto getAccount() {
        return account;
    }

    public void setAccount(DomainEntityDto account) {
        this.account = account;
    }

    public DomainEntityDto getFirstScene() {
        return firstScene;
    }

    public void setFirstScene(DomainEntityDto firstScene) {
        this.firstScene = firstScene;
    }

    public Set<DomainEntityDto> getScenes() {
        return scenes;
    }

    public void setScenes(Set<DomainEntityDto> scenes) {
        this.scenes = scenes;
    }

    public Set<DomainEntityDto> getFiles() {
        return files;
    }

    public void setFiles(Set<DomainEntityDto> files) {
        this.files = files;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}
