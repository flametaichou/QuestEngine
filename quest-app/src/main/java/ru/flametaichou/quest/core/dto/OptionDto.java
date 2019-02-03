package ru.flametaichou.quest.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionDto extends DomainEntityDto {

    private String name;
    private DomainEntityDto scene;
    private DomainEntityWithNameDto targetScene;

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

    public DomainEntityWithNameDto getTargetScene() {
        return targetScene;
    }

    public void setTargetScene(DomainEntityWithNameDto targetScene) {
        this.targetScene = targetScene;
    }
}
