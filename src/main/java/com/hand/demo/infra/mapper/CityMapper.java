package com.hand.demo.infra.mapper;

import org.apache.ibatis.annotations.Param;
import com.hand.demo.domain.entity.City;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * 城市 DAO 接口类
 */
@Repository
public interface CityMapper {

    List<City> findAllCity();

    City findCityById(@Param("id") Long id);

    City findCityByName(@Param("cityName") String cityName);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}
