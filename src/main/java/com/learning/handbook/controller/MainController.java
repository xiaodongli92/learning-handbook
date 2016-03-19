package com.learning.handbook.controller;

import com.learning.handbook.interceptor.AuthPermission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiaodong on 2016/3/19.
 */
@Controller
@RequestMapping("")
public class MainController {

    @AuthPermission
    @RequestMapping("main")
    public String main() {
        return "main";
    }
}
