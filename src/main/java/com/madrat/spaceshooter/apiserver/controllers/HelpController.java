package com.madrat.spaceshooter.apiserver.controllers;

import com.madrat.spaceshooter.apiserver.resourcereprs.Help;
import com.madrat.spaceshooter.apiserver.utils.logger.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelpController {
    @RequestMapping({"/", "/root", "/help"})
    public Help help(HttpServletRequest request) {
        Logger.i("request to: " + request.getRequestURI());
        return new Help();
    }
}
