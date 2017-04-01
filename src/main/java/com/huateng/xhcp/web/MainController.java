package com.huateng.xhcp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sam.pan on 2017/2/28.
 */
@Controller
public class MainController {

    @RequestMapping("/main")
    public String mainPage(){
        return "main";
    }
}
