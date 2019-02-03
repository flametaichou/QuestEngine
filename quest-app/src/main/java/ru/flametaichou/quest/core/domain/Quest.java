package ru.flametaichou.quest.core.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "QUEST")
@AttributeOverride(name = "id", column = @Column(name = "QUEST_ID"))
public class Quest extends DomainEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "UNIQUE_CODE", nullable = false)
    private String uniqueCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIRST_SCENE_ID")
    private Scene firstScene;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quest")
    private Set<Scene> scenes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quest")
    private Set<QuestFile> files = new HashSet<>();

    @Column(name = "AVAILABLE")
    private Boolean available;

    public Quest() {
        this.uniqueCode = UUID.randomUUID().toString();
    }

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Scene getFirstScene() {
        return firstScene;
    }

    public void setFirstScene(Scene firstScene) {
        this.firstScene = firstScene;
    }

    public Set<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(Set<Scene> scenes) {
        this.scenes = scenes;
    }

    public Set<QuestFile> getFiles() {
        return files;
    }

    public void setFiles(Set<QuestFile> files) {
        this.files = files;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
