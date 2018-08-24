package com.hand.demo.api.controller.v1;

import com.hand.demo.Application;
import com.hand.demo.domain.Page;
import com.hand.demo.domain.entity.City;
import com.hand.demo.api.service.CityService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 */
@Controller
@RequestMapping("/v1/cities")
public class CityController {

    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private CityService cityService;

    //返回视图
    @GetMapping(value = "/list1")
    public String findAllCity(Model model,Page page) {
        //查看sort的值
        logger.debug("页码："+page.getPageNum());
        logger.debug("每页："+page.getPageSize());
        logger.debug("排序"+page.getSort());
        List<City> cityList = cityService.findAllCity(page);
        model.addAttribute("cityList", cityList);
        return "cityList";
    }
    //返回json
    @GetMapping(value = "/list2")
    @ResponseBody
    public List<City> findAllCity2(Page page) {
        return cityService.findAllCity(page);
    }
    //返回视图
    @GetMapping("/list3")
    public String findAllCity3(Model model,Page page) {
        List<City> list = cityService.findAllCity(page);
        model.addAttribute("list", list);
        return "cityAll";
    }

    //根据id查询，返回json
    @GetMapping("/id1/{id}")
    @ResponseBody
    public City findCityById(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }
    //根据id查询，返回视图
    @GetMapping("/id2/{id}")
    public String findCityById2(Model model, @PathVariable("id") Long id) {
        model.addAttribute("scity", cityService.findCityById(id));
        return "city";
    }

    //
    @GetMapping("/name/{cityName}")
    @ResponseBody
    public City findCityByName(@PathVariable("cityName") String cityName) {
        return cityService.findCityByName(cityName);
    }

    @PostMapping("/api")
    @ResponseBody
    public void createCity(@RequestBody City city) {
        cityService.saveCity(city);
    }

    @PutMapping("/api")
    @ResponseBody
    public void modifyCity(@RequestBody City city) {
        cityService.updateCity(city);
    }

    @DeleteMapping("/api/{id}")
    public void modifyCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }
}