package ru.flametaichou.quest.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.flametaichou.quest.core.dao.SceneDao;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.dto.SceneDto;
import ru.flametaichou.quest.core.domain.Scene;

public class SceneServiceImpl implements SceneService {

    private final Logger logger = LoggerFactory.getLogger(SceneServiceImpl.class);

    @Autowired
    private SceneDao sceneDao;

    private ConfigurableMapper mapper = new ConfigurableMapper();

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public List<SceneDto> listSceneDtos() {

        Account currentAccount = accountService.getCurrentUser();

        List<Scene> rawScenes = sceneDao.listScenes();
        List<Scene> scenes = new ArrayList<Scene>();

        // Права. Выбираем только сцены юзера если это не админ
        if (accountService.isSuperuser(currentAccount)) {
            scenes = rawScenes;
        } else {

        }

        List<SceneDto> dtos = new ArrayList<SceneDto>();
        for (Scene scene : scenes) {
            SceneDto dto = new SceneDto();
            dto.setId(scene.getId());
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    @Transactional
    public void createScene(SceneDto dto) {
        Scene scene = new Scene();
        scene.setName(dto.getName());
        sceneDao.saveOrUpdate(scene);
    }

    @Override
    @Transactional
    public void deleteScene(Long id) {
        Scene scene = sceneDao.findById(id);
        if (Objects.isNull(scene)) {
            logger.error("Could not find scene id:{}!", id);
            return;
        }
        sceneDao.delete(scene);
    }

    @Override
    public Scene findByExternalId(String externalId) {
        return sceneDao.findByExternalId(externalId);
    }

    @Override
    @Transactional
    public void updateScene(Scene scene) {
        sceneDao.saveOrUpdate(scene);
    }
}
