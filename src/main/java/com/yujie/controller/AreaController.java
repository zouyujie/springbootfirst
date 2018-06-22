package com.yujie.controller;

import com.yujie.model.Area;
import com.yujie.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@Api(value = "区域操作controller", description = "区域相关的操作", tags = {"区域模块校验接口"})
@RestController
//@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaService areaService;
/*    @RequestMapping(value = "/get", produces = {"application/json;charset=UTF-8"})
    public Map<String,Object> getArea(@PathParam("areaId") Integer areaId){
        Map<String,Object> modelMap= new HashMap<String,Object>() ;
        modelMap.put("area",areaService.getArea(areaId));
        return modelMap;
    }*/
    @ApiOperation(value="获取区域详细信息", notes="根据url的id来获取区域详细信息")
    @ApiImplicitParam(name = "Id", value = "区域ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/get/{Id}",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Map<String,Object> getAreaApi(@PathVariable("Id") Integer areaId){
        Map<String,Object> modelMap= new HashMap<String,Object>() ;
        modelMap.put("area",areaService.getArea(areaId));
        return modelMap;
    }
    @ApiOperation(value="创建区域", notes="根据Area对象创建区域")
    @ApiImplicitParam(name = "area", value = "区域详细实体area", required = true, dataType = "Area")
    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public Map<String,Object> addArea(Area area){
        Map<String,Object> modelMap= new HashMap<String,Object>() ;
        modelMap.put("success",areaService.addArea(area));
        return modelMap;
    }
    @ApiOperation(value="修改区域", notes="根据Area对象修改区域")
    @ApiImplicitParam(name = "area", value = "区域详细实体area", required = true, dataType = "Area")
    @RequestMapping(value = "/edit",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public Map<String,Object> editArea(Area area){
        Map<String,Object> modelMap= new HashMap<String,Object>() ;
        modelMap.put("success",areaService.addArea(area));
        return modelMap;
    }
    @ApiOperation(value="获取区域列表", notes="获取区域列表")
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "pageNum", value = "第多少页", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页取多少条记录", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "/all/{pageNum}/{pageSize}",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public Object findAllArea(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return areaService.findAllArea(pageNum,pageSize);
    }
    @GetMapping(value="/del/{areaId}")
    public Map<String,Object> deleteArea(@PathVariable Integer areaId){
        Map<String,Object> modelMap= new HashMap<String,Object>() ;
        modelMap.put("success",areaService.deleteArea(areaId));
        return modelMap;
    }
    @GetMapping("/test")
    @ApiIgnore//使用该注解忽略这个API
    public String Test(){
        return "test";
    }


    /**
     * 获取所有区域信息
     * @return
     */
    @GetMapping("/getAll")
    public Object selectAllArea(){
        return  areaService.selectAllArea();
    }
    /**
     * 获取所有区域信息
     * @return
     */
    @GetMapping("/getAll2")
    public Object selectAllArea2(){
        return  areaService.selectAllArea2();
    }
}
