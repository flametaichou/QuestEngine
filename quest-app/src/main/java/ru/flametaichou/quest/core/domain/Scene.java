package ru.flametaichou.quest.core.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "SCENE")
@AttributeOverride(name = "id", column = @Column(name = "SCENE_ID"))
public class Scene extends DomainEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TEXT")
    private String text;

    @Type(type = "pg-uuid")
    @Column(name = "UNIQUE_CODE", columnDefinition = "uuid", nullable = false)
    private UUID uniqueCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BACKGROUND_FILE_ID")
    private QuestFile backgroundFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PORTRAIT_FILE_ID")
    private QuestFile portraitFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUEST_ID", nullable = false)
    private Quest quest;

    @Column(name = "TYPE", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private SceneType type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "scene")
    private Set<Option> options = new HashSet<>();

    public Scene() {
        this.uniqueCode = UUID.randomUUID();
    }

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

    public QuestFile getBackgroundFile() {
        return backgroundFile;
    }

    public void setBackgroundFile(QuestFile backgroundFile) {
        this.backgroundFile = backgroundFile;
    }

    public QuestFile getPortraitFile() {
        return portraitFile;
    }

    public void setPortraitFile(QuestFile portraitFile) {
        this.portraitFile = portraitFile;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public SceneType getType() {
        return type;
    }

    public void setType(SceneType type) {
        this.type = type;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public enum SceneType {
        DIALOG,
        PICTURE,
        END
    }
}
