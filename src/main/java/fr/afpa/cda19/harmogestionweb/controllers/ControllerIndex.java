package fr.afpa.cda19.harmogestionweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerIndex {
    @GetMapping("/index")
    public static String pageIndex() {
        return "index";
    }
}
