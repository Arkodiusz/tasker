package com.crud.tasker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("variable", "My Thymeleaf variable");
        model.put("one", 1);
        model.put("two", 2);
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/wakeup")
    public ResponseEntity<String> wakeUp() {
        return new ResponseEntity<>("\"Tasker app is awake\"", HttpStatus.OK);
    }
}
