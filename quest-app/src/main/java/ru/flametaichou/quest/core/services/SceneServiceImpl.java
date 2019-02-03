package ru.flametaichou.quest.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.flametaichou.quest.core.dao.SceneDao;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.domain.QuestFile;
import ru.flametaichou.quest.core.dto.QuestDto;
import ru.flametaichou.quest.core.dto.SceneDto;
import ru.flametaichou.quest.core.domain.Scene;
import ru.flametaichou.quest.utils.EnumUtils;
import ru.flametaichou.quest.utils.QuestStringUtils;

public class SceneServiceImpl implements SceneService {

    private final Logger logger = LoggerFactory.getLogger(SceneServiceImpl.class);

    @Autowired
    private SceneDao sceneDao;

    private ConfigurableMapper mapper = new ConfigurableMapper();

    @Autowired
    private AccountService accountService;

    @Autowired
    private QuestService questService;

    @Autowired
    private QuestFileService questFileService;

    @Override
    @Transactional
    public List<SceneDto> listSceneDtos(Account account, Quest quest) {

        List<Scene> rawScenes = sceneDao.listScenes(quest);
        List<SceneDto> dtos = new ArrayList<SceneDto>();

        // Права. Выбираем только сцены юзера если это не админ
        if (accountService.hasPermissionsTo(account, quest)) {
            for (Scene scene : rawScenes) {
                SceneDto dto = mapper.map(scene, SceneDto.class);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Override
    @Transactional
    public SceneDto findDtoById(Long id) {
        Scene scene = sceneDao.findById(id);
        if (Objects.nonNull(scene)) {
            if (scene.getQuest().getAvailable()) {
                SceneDto sceneDto = mapper.map(scene, SceneDto.class);
                return sceneDto;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void createScene(Account account, SceneDto dto) {
        Scene scene = new Scene();
        updateScene(account, scene, dto);
    }

    @Override
    @Transactional
    public void updateScene(Account account, SceneDto dto) {
        Scene scene = sceneDao.findById(dto.getId());
        if (Objects.isNull(sceneDao)) {
            logger.error("Could not find scene id:{}!", dto.getId());
            return;
        }
        updateScene(account, scene, dto);
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
    @Transactional(readOnly = true)
    public Scene findByExternalId(String externalId) {
        return sceneDao.findByExternalId(externalId);
    }

    @Override
    @Transactional(readOnly = true)
    public Scene findById(Long id) {
        return sceneDao.findById(id);
    }

    @Override
    @Transactional
    public void updateScene(Scene scene) {
        sceneDao.saveOrUpdate(scene);
    }

    private void updateScene(Account account, Scene scene, SceneDto dto) {
        Quest quest = questService.findQuestById(dto.getQuest().getId());
        if (accountService.hasPermissionsTo(account, quest)) {
            scene.setName(dto.getName());
            scene.setQuest(quest);
            if (Objects.nonNull(dto.getPortraitFile())) {
                QuestFile portraitFile = questFileService.findByCode(dto.getPortraitFile().getUniqueCode());
                scene.setPortraitFile(portraitFile);
            }
            if (Objects.nonNull(dto.getBackgroundFile())) {
                QuestFile backgroundFile = questFileService.findByCode(dto.getBackgroundFile().getUniqueCode());
                scene.setBackgroundFile(backgroundFile);
            }
            //scene.setOptions();
            scene.setText(dto.getText());
            scene.setType(Scene.SceneType.valueOf(dto.getType()));
            sceneDao.saveOrUpdate(scene);
        }
    }
}
