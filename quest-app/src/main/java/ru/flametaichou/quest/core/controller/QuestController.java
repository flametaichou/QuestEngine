package ru.flametaichou.quest.core.controller;

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
import ru.flametaichou.quest.core.dto.QuestDto;
import ru.flametaichou.quest.core.services.QuestService;
import ru.flametaichou.quest.core.services.AccountService;

@Controller
public class QuestController {

    Logger logger = LoggerFactory.getLogger(QuestController.class);

    @Autowired
    private QuestService questService;

    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @RequestMapping(value = "/engine/data/quests", method = RequestMethod.GET)
    @ResponseBody
    public List<QuestDto> getQuestsList(Authentication authentication) {
        Account account = accountService.findUserByName(authentication.getName());
        return questService.listQuestDtos(account);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @RequestMapping(value = "/engine/data/submitQuest", method = RequestMethod.POST)
    @ResponseBody
    public void submitQuest(Authentication authentication, @RequestBody QuestDto dto) {

        Account account = accountService.findUserByName(authentication.getName());
        if (Objects.isNull(dto.getId())) {
            logger.info("Registering quest {}...", dto.getName());
            questService.createQuest(account, dto);
        } else {
            logger.info("Updating quest {}...", dto.getName());
            questService.updateQuest(account, dto);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @RequestMapping(value = "/engine/data/loadQuestByRef", method = RequestMethod.GET)
    @ResponseBody
    public QuestDto loadQuestByRef(Authentication authentication, @RequestParam(name = "questRef") String questRef) {
        Account account = accountService.findUserByName(authentication.getName());
        return questService.findQuestDtoByUniqueCode(account, questRef);
    }

    @RequestMapping(value = "/viewer/data/loadQuest", method = RequestMethod.GET)
    @ResponseBody
    public QuestDto loadQuest(@RequestParam(name = "questRef") String questRef) {
        return questService.findQuestDtoByUniqueCode(questRef);
    }

    @RequestMapping(value = "/viewer/data/quests", method = RequestMethod.GET)
    @ResponseBody
    public List<QuestDto> getQuestsList() {
        return questService.listQuestDtos();
    }







    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/engine/admin/data/quest/delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteQuest(Account account, @RequestParam(name = "id") Long id) {
        logger.info("Deleting quest id:{}...", id);
        try {
            questService.deleteQuest(account, id);
        } catch (Exception e) {
            logger.error("Deleting quest id:{} failed!", id);
            logger.error("Error: {}", ExceptionUtils.getRootCauseMessage(e));
        }
    }
}
