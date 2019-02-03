package ru.flametaichou.quest.core.services;

import java.util.List;

import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.domain.Scene;
import ru.flametaichou.quest.core.dto.SceneDto;

public interface SceneService {

    List<SceneDto> listSceneDtos(Account account, Quest quest);
    SceneDto findDtoById(Long id);
    void createScene(Account account, SceneDto dto);
    void updateScene(Account account, SceneDto dto);
    void deleteScene(Long id);
    Scene findByExternalId(String externalId);
    Scene findById(Long id);
    void updateScene(Scene scene);
}
