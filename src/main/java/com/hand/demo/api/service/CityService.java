package com.hand.demo.api.service;

import com.hand.demo.domain.Page;
import com.hand.demo.domain.entity.City;

import java.util.List;

/**
 * 城市业务逻辑接口类
 */
public interface CityService {


    List<City> findAllCity(Page page);

    City findCityById(Long id);

    City findCityByName(String cityName);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}
