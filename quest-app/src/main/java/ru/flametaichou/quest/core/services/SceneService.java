package ru.flametaichou.quest.core.services;

import java.util.List;

import ru.flametaichou.quest.core.domain.Scene;
import ru.flametaichou.quest.core.dto.SceneDto;

public interface SceneService {

    List<SceneDto> listSceneDtos();
    void createScene(SceneDto dto);
    void deleteScene(Long id);
    Scene findByExternalId(String externalId);
    void updateScene(Scene scene);
}
