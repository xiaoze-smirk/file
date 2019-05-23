package com.xiaoze.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexController
 *
 * @author xiaoze
 * @date 2018/6/3
 *
 */
@Controller
public class IndexController {

    /**
     * http://localhost:9090/file
     */
    @GetMapping("/")
    public String root() {
        return "index";
    }

}
