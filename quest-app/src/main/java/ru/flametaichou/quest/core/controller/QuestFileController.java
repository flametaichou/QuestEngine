package ru.flametaichou.quest.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.dto.ImageDto;
import ru.flametaichou.quest.core.dto.QuestFileDto;
import ru.flametaichou.quest.core.services.AccountService;
import ru.flametaichou.quest.core.services.QuestFileService;

@Controller
public class QuestFileController {

    Logger logger = LoggerFactory.getLogger(QuestFileController.class);

    @Autowired
    private QuestFileService questFileService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ApplicationContext applicationContext;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/engine/admin/data/allQuestFiles", method = RequestMethod.GET)
    @ResponseBody
    public List<QuestFileDto> getAllQuestFileList() {
        return questFileService.listAllQuestFileDtos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/engine/admin/data/submitQuestFile", method = RequestMethod.POST)
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
    @RequestMapping(value = "/engine/admin/data/questFile/delete", method = RequestMethod.POST)
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

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @RequestMapping(value = "/engine/data/quest/{questId}/questFile/save", method = RequestMethod.POST)
    @ResponseBody
    public void saveQuestFile(Authentication authentication, @PathVariable("questId") Long questId, @RequestBody @RequestParam("file") MultipartFile file) throws IOException {
        Account account = accountService.findUserByName(authentication.getName());
        questFileService.saveQuestFile(account, file, questId);

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @RequestMapping(value = "/engine/data/questFiles", method = RequestMethod.GET)
    @ResponseBody
    public List<QuestFileDto> getQuestFileList(Authentication authentication, @RequestParam("questId") Long questId) {
        Account account = accountService.findUserByName(authentication.getName());
        return questFileService.listQuestFileDtos(account, questId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @RequestMapping(value = "/engine/data/questFile/{questFileCode}", method = RequestMethod.GET)
    @ResponseBody
    public ImageDto getImage(Authentication authentication, @PathVariable("questFileCode") UUID questFileCode) {
        //Account account = accountService.findUserByName(authentication.getName());
        return questFileService.getImage(questFileCode);
    }

    @RequestMapping(value = "/viewer/data/questFile/{questFileCode}", method = RequestMethod.GET)
    @ResponseBody
    public ImageDto getViewerImage(@PathVariable("questFileCode") UUID questFileCode) {
        return questFileService.getImage(questFileCode);
    }
}
