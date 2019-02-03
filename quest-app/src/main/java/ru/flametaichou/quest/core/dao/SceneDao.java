package ru.flametaichou.quest.core.dao;

import java.util.List;

import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.domain.Scene;

/**
 * @date 24.08.18
 */
public interface SceneDao {

    void saveOrUpdate(Scene scene);
    Scene findById(long id);
    void delete(Scene scene);
    List<Scene> listAllScenes();
    List<Scene> listScenes(Quest quest);
    Scene findByExternalId(String externalId);
}
