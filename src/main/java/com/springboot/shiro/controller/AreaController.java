package com.springboot.shiro.controller;

import com.springboot.shiro.service.AreaService;
import com.springboot.shiro.util.ActionReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xutianhong
 * @Date 2021/3/17 11:33 上午
 */
@Controller
@Slf4j
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @ResponseBody
    @RequestMapping(value = "/province", method = RequestMethod.GET)
    private ActionReturnUtil listProvinces(@RequestParam(value = "province", required = false) String province)
        throws Exception {
        if (!StringUtils.isEmpty(province)) {
            return ActionReturnUtil.returnSuccessWithData(areaService.getCity(province));
        }
        return ActionReturnUtil.returnSuccessWithData(areaService.getProvince());
    }

}
