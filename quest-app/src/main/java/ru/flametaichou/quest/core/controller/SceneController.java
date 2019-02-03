package ru.flametaichou.quest.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.domain.Quest;
import ru.flametaichou.quest.core.domain.Scene;
import ru.flametaichou.quest.core.dto.*;
import ru.flametaichou.quest.core.services.AccountService;
import ru.flametaichou.quest.core.services.QuestService;
import ru.flametaichou.quest.core.services.SceneService;
import ru.flametaichou.quest.utils.EnumUtils;

@Controller
public class SceneController {

    Logger logger = LoggerFactory.getLogger(SceneController.class);

    @Autowired
    private SceneService sceneService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private QuestService questService;

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @RequestMapping(value = "/engine/data/scenes", method = RequestMethod.GET)
    @ResponseBody
    public List<SceneDto> getSceneList(Authentication authentication, @RequestParam(name = "questId") Long questId) {
        Account account = accountService.findUserByName(authentication.getName());
        Quest quest = questService.findQuestById(questId);
        return sceneService.listSceneDtos(account, quest);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @RequestMapping(value = "/engine/data/sceneTypes", method = RequestMethod.GET)
    @ResponseBody
    public List<EnumEntityDto> getSceneTypes() {
        List<EnumEntityDto> result = new ArrayList<EnumEntityDto>();
        Scene.SceneType[] types = Scene.SceneType.values();
        for (Scene.SceneType type : types) {
            result.add(EnumUtils.getDtoFromEnum(type));
        }
        return result;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @RequestMapping(value = "/engine/data/submitScene", method = RequestMethod.POST)
    @ResponseBody
    public void submitScene(Authentication authentication, @RequestBody SceneDto dto) {
        Account account = accountService.findUserByName(authentication.getName());
        if (Objects.isNull(dto.getId())) {
            logger.info("Registering scene {}...", dto.getName());
            sceneService.createScene(account, dto);
        } else {
            logger.info("Updating scene {}...", dto.getName());
            sceneService.updateScene(account, dto);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/engine/admin/data/scene/delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteScene(@RequestParam(name = "id") Long id) {
        logger.info("Deleting scene id:{}...", id);
        try {
            sceneService.deleteScene(id);
        } catch (Exception e) {
            logger.error("Deleting scene id:{} failed!", id);
            logger.error("Error: {}", ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @RequestMapping(value = "/viewer/data/loadScene", method = RequestMethod.GET)
    @ResponseBody
    public SceneDto loadScene(@RequestParam(name = "sceneId") Long sceneId) {
        return sceneService.findDtoById(sceneId);
    }
}
