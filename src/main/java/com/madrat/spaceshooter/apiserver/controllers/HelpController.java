package com.madrat.spaceshooter.apiserver.controllers;

import com.madrat.spaceshooter.apiserver.resourcereprs.Help;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelpController {
    @RequestMapping({"/", "/root", "/help"})
    public Help help() {
        return new Help();
    }
}
