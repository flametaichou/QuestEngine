package ru.flametaichou.quest.core.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.flametaichou.quest.core.dto.*;
import ru.flametaichou.quest.core.services.OptionService;
import ru.flametaichou.quest.core.services.SceneService;

@Controller
public class SceneController {

    Logger logger = LoggerFactory.getLogger(SceneController.class);

    @Autowired
    private SceneService sceneService;

    @Autowired
    private OptionService optionService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/data/scenes", method = RequestMethod.GET)
    @ResponseBody
    public List<SceneDto> getSceneList() {
        return sceneService.listSceneDtos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/data/submitScene", method = RequestMethod.POST)
    @ResponseBody
    public void submitScene(@RequestBody SceneDto dto) {
        logger.info("Registering scene {}...", dto.getName());
        try {
            sceneService.createScene(dto);
        } catch (Exception e) {
            logger.error("Registering scene {} failed!", dto.getName());
            logger.error("Error: {}", ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/data/scene/delete", method = RequestMethod.POST)
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
}
