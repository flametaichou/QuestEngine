package ru.flametaichou.quest.core.controller;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.flametaichou.quest.core.dto.AccountDto;
import ru.flametaichou.quest.core.dto.AccountRoleDto;
import ru.flametaichou.quest.core.services.RoleService;
import ru.flametaichou.quest.core.services.AccountService;

/**
 * @date 27.08.18
 */
@Controller
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/data/accounts", method = RequestMethod.GET)
    @ResponseBody
    public List<AccountDto> getAccountsList() {
        return accountService.listAccountDtos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/data/roles", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getRoles() {
        return roleService.listRoles();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/data/submitUser", method = RequestMethod.POST)
    @ResponseBody
    public void submitUser(@RequestParam(name = "id", required = false) Long id,
                           @RequestParam(name = "username", required = false) String username,
                           @RequestParam(name = "password", required = false) String password,
                           @RequestParam(name = "role", required = false) String role) {
        AccountDto dto = new AccountDto();
        dto.setId(id);
        dto.setUsername(username);
        dto.setPassword(password);
        AccountRoleDto roleDto = new AccountRoleDto();
        roleDto.setRole(role);
        dto.setUserRole(roleDto);

        if (Objects.isNull(id)) {
            logger.info("Registering user {}...", dto.getUsername());
            try {
                accountService.registerNewUserAccount(dto);
            } catch (Exception e) {
                logger.error("Registering user {} failed!", dto.getUsername());
                logger.error("Error: {}", ExceptionUtils.getRootCauseMessage(e));
            }
        } else {
            logger.info("Updating user {}...", dto.getUsername());
            try {
                accountService.updateUser(dto);
            } catch (Exception e) {
                logger.error("Updating user {} failed!", dto.getUsername());
                logger.error("Error: {}", ExceptionUtils.getRootCauseMessage(e));
            }
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin/data/account/delete", method = RequestMethod.POST)
    @ResponseBody
    public void submitUser(@RequestParam(name = "username") String username) {
        logger.info("Deleting user {}...", username);
        try {
            accountService.deleteUser(username);
        } catch (Exception e) {
            logger.error("Deleting user {} failed!", username);
            logger.error("Error: {}", ExceptionUtils.getRootCauseMessage(e));
        }
    }
}
