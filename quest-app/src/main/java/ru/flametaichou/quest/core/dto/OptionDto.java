package ru.flametaichou.quest.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionDto extends DomainEntityDto {

    private String name;
    private DomainEntityDto scene;
    private DomainEntityDto targetSceneId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DomainEntityDto getScene() {
        return scene;
    }

    public void setScene(DomainEntityDto scene) {
        this.scene = scene;
    }

    public DomainEntityDto getTargetSceneId() {
        return targetSceneId;
    }

    public void setTargetSceneId(DomainEntityDto targetSceneId) {
        this.targetSceneId = targetSceneId;
    }
}
