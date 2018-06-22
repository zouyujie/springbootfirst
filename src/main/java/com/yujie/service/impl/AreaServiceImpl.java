package com.yujie.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.yujie.mapper.AreaMapper;
import com.yujie.model.Area;
import com.yujie.service.AreaService;
import com.yujie.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "areaService")
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;

    @Override
    public int addArea(Area area) {
        return areaMapper.insertSelective(area);
    }

    @Override
    public int editArea(Area area) {
        return areaMapper.updateByPrimaryKeySelective(area);
    }

    @Override
    public List<Area> findAllArea(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return areaMapper.selectAllArea();
    }

    @Override
    public int deleteArea(Integer areaId) {
        return areaMapper.deleteByPrimaryKey(areaId);
    }

    @Override
    public Area getArea(Integer areaId) {
        return areaMapper.selectByPrimaryKey(areaId);
    }

    @Autowired
    private RedisService redisService;
    private JSONObject json = new JSONObject();

    /**
     * 从缓存中获取区域列表
     *
     * @return
     */
    private List<Area> getAreaList() {
        String result = redisService.get("redis_obj_area");
        if (result == null || result.equals("")) {
            return null;
        } else {
            return json.parseArray(result, Area.class);
        }
    }

    @Override
    public List<Area> selectAllArea() {
        List<Area> list = getAreaList();
        if (list == null) {
            synchronized (this) {
                list = getAreaList(); //双重检测锁
                if (list == null) {
                    list = areaMapper.selectAllArea();
                    redisService.set("redis_obj_area", json.toJSONString(list));
                    System.out.println("请求的数据库。。。。。。");
                } else {
                    System.out.println("请求的缓存。。。。。。");
                }
            }
        } else {
            System.out.println("请求的缓存。。。。。。");
        }
        return list;
    }

    @Override
    public List<Area> selectAllArea2() {
        List<Area> list = getAreaList();
        if (list == null) {
            list = areaMapper.selectAllArea();
            redisService.set("redis_obj_area", json.toJSONString(list));
            System.out.println("请求的数据库。。。。。。");
        } else {
            System.out.println("请求的缓存。。。。。。");
        }
        return list;
    }
}
