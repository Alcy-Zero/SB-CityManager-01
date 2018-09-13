package com.hand.demo.api.controller.v1;

import com.hand.demo.Application;
import com.hand.demo.domain.entity.City;
import com.hand.demo.app.service.CityService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 */
@Controller
@RequestMapping("/v1/cities")
public class CityController {

    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private CityService cityService;

    long s1 = 0;
    long s2 = 0;

    //返回视图
    @GetMapping(value = "/list1")
    public String findAllCity(Model model) {
        List<City> cityList = cityService.findAllCity();
        model.addAttribute("cityList", cityList);
        return "cityList";
    }
    //返回json
    @GetMapping(value = "/list2")
    @ResponseBody
    public List<City> findAllCity2() {
        s1 = System.currentTimeMillis();
        List<City> cities = cityService.findAllCity();
        s2 = System.currentTimeMillis();
        return cities;
    }
    //返回视图
    @GetMapping("/list3")
    public String findAllCity3(Model model) {
        List<City> list = cityService.findAllCity();
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

    public long getS1() {
        return s1;
    }

    public void setS1(long s1) {
        this.s1 = s1;
    }

    public long getS2() {
        return s2;
    }

    public void setS2(long s2) {
        this.s2 = s2;
    }
}
