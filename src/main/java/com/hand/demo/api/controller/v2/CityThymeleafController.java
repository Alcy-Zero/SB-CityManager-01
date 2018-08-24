package com.hand.demo.api.controller.v2;

import com.hand.demo.app.service.CityService;
import com.hand.demo.domain.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v2/cities")
public class CityThymeleafController {

    @Autowired
    private CityService cityService;

    @GetMapping("/list")
    public String list(Model model) {

        City single = new City(101L,"Aa");
        model.addAttribute("singleCity", single);

        List<City> cityList1 = cityService.findAllCity();
        model.addAttribute("cityList1", cityList1);

        return "list";
    }
}
