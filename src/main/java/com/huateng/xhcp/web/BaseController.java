package com.huateng.xhcp.web;

import com.huateng.xhcp.service.nav.NavInfoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sam.pan on 2017/3/10.
 */
public class BaseController {
    private @Autowired NavInfoService navInfoService;

}
