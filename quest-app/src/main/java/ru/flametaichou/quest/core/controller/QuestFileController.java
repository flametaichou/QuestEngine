package ru.flametaichou.quest.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.flametaichou.quest.core.dto.QuestFileDto;
import ru.flametaichou.quest.core.services.QuestFileService;

@Controller
public class QuestFileController {

    Logger logger = LoggerFactory.getLogger(QuestFileController.class);

    @Autowired
    private QuestFileService questFileService;

    @Autowired
    private ApplicationContext applicationContext;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/data/questFiles", method = RequestMethod.GET)
    @ResponseBody
    public List<QuestFileDto> getQuestFileList() {
        return questFileService.listQuestFileDtos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/data/submitQuestFile", method = RequestMethod.POST)
    @ResponseBody
    public void submitQuestFile(@RequestParam(name = "name") String name) {
        QuestFileDto dto = new QuestFileDto();
        dto.setName(name);
        logger.info("Registering questFile {}...", dto.getName());
        try {
            questFileService.createQuestFile(dto);
        } catch (Exception e) {
            logger.error("Registering questFile {} failed!", dto.getName());
            logger.error("Error: {}", ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/data/questFile/delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteQuestFile(@RequestParam(name = "id") Long id) {
        logger.info("Deleting questFile id:{}...", id);
        try {
            questFileService.deleteQuestFile(id);
        } catch (Exception e) {
            logger.error("Deleting questFile id:{} failed!", id);
            logger.error("Error: {}", ExceptionUtils.getRootCauseMessage(e));
        }
    }
}
