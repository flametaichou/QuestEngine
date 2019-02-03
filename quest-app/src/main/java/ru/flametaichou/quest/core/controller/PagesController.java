package ru.flametaichou.quest.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PagesController {

    @RequestMapping(value = { "/", "/index**" }, method = RequestMethod.GET)
    public String indexPage() { return "index"; }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(value = { "/engine" }, method = RequestMethod.GET)
    public String enginePage() { return "engine"; }

    @RequestMapping(value = "/engine/403", method = RequestMethod.GET)
    public String accessDeniedPage() {
        return "403";
    }

    @RequestMapping(value = "/engine/templates/404", method = RequestMethod.GET)
    public String pageNotFound() {
        return "404";
    }

    @RequestMapping(value = "/engine/templates/main", method = RequestMethod.GET)
    public String mainPage() {
        return "main";
    }

    @RequestMapping(value = "/engine/templates/dashboard", method = RequestMethod.GET)
    public String dashboardPage() {
        return "dashboard";
    }

    @RequestMapping(value = "/engine/templates/modal/formQuest", method = RequestMethod.GET)
    public String createNewQuestModal() {
        return "modal/formQuest";
    }

    @RequestMapping(value = "/engine/templates/modal/formOption", method = RequestMethod.GET)
    public String openOptionModal() {
        return "modal/formOption";
    }

    @RequestMapping(value = "/engine/templates/modal/formUpload", method = RequestMethod.GET)
    public String openUploadModal() {
        return "modal/formUpload";
    }

    @RequestMapping(value = "/engine/templates/modal/formImages", method = RequestMethod.GET)
    public String openImagesModal() {
        return "modal/formImages";
    }

    @RequestMapping(value = "/engine/templates/directive/image", method = RequestMethod.GET)
    public String directiveImage() {
        return "directives/image";
    }

    @RequestMapping(value = "/engine/templates/questEditor", method = RequestMethod.GET)
    public String questEditorPage() {
        return "questEditor";
    }

    @RequestMapping(value = "/engine/templates/admin/accounts", method = RequestMethod.GET)
    public String accountsPage() {
        return "accounts";
    }



    @RequestMapping(value = { "/viewer" }, method = RequestMethod.GET)
    public String viewerPage() { return "viewer"; }

    @RequestMapping(value = { "/viewer/templates/quest" }, method = RequestMethod.GET)
    public String questWindow() { return "questWindow"; }

    @RequestMapping(value = { "/viewer/templates/main" }, method = RequestMethod.GET)
    public String questMain() { return "questMain"; }

    @RequestMapping(value = "/viewer/templates/directive/image", method = RequestMethod.GET)
    public String questDirectiveImage() {
        return "directives/image";
    }

    @RequestMapping(value = "/viewer/403", method = RequestMethod.GET)
    public String accessDeniedPageViewer() {
        return "403";
    }

    @RequestMapping(value = "/viewer/templates/404", method = RequestMethod.GET)
    public String pageNotFoundViewer() {
        return "404";
    }
}
