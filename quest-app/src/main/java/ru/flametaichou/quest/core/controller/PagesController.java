package ru.flametaichou.quest.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PagesController {

    @RequestMapping(value = { "/", "/index**" }, method = RequestMethod.GET)
    public String indexPage() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/templates/main", method = RequestMethod.GET)
    public String mainPage() {
        return "main";
    }

    @RequestMapping(value = "/templates/dashboard", method = RequestMethod.GET)
    public String dashboardPage() {
        return "dashboard";
    }

    @RequestMapping(value = "/templates/admin/accounts", method = RequestMethod.GET)
    public String accountsPage() {
        return "accounts";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDeniedPage() {
        return "403";
    }

    @RequestMapping(value = "templates/404", method = RequestMethod.GET)
    public String pageNotFound() {
        return "404";
    }

    @RequestMapping(value = "/templates/modal/createNewQuest", method = RequestMethod.GET)
    public String createNewQuestModal() {
        return "modal/createQuest";
    }

    @RequestMapping(value = "/templates/questEditor", method = RequestMethod.GET)
    public String questEditorPage() {
        return "questEditor";
    }
}
