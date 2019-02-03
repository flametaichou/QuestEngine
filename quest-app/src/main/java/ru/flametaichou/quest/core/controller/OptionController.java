package ru.flametaichou.quest.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.flametaichou.quest.core.domain.Account;
import ru.flametaichou.quest.core.dto.OptionDto;
import ru.flametaichou.quest.core.services.AccountService;
import ru.flametaichou.quest.core.services.OptionService;

import java.util.Objects;

@Controller
public class OptionController {

    Logger logger = LoggerFactory.getLogger(SceneController.class);

    @Autowired
    private OptionService optionService;

    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")
    @RequestMapping(value = "/engine/data/submitOption", method = RequestMethod.POST)
    @ResponseBody
    public void submitOption(Authentication authentication, @RequestBody OptionDto dto) {
        Account account = accountService.findUserByName(authentication.getName());
        if (Objects.isNull(dto.getId())) {
            logger.info("Registering option {}...", dto.getName());
            optionService.createOption(account, dto);
        } else {
            logger.info("Updating option {}...", dto.getName());
            optionService.updateOption(account, dto);
        }
    }
}
