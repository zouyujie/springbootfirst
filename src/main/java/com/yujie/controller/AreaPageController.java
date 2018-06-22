package com.yujie.controller;

import com.yujie.model.Area;
import com.yujie.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AreaPageController{
    @Autowired
    private AreaService areaService;

    @GetMapping("/addArea")
    public String addArea(Model model) {
        model.addAttribute("area", new Area());
        return "addArea";
    }
    @RequestMapping(value = "/addArea",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String addArea(@Valid @ModelAttribute Area area, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "addArea";
        }else{
            Map<String,Object> modelMap= new HashMap<String,Object>() ;
            modelMap.put("success",areaService.addArea(area));
            return "result";
        }
    }

}
