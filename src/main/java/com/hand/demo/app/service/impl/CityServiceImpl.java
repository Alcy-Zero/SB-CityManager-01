package com.hand.demo.app.service.impl;

import com.hand.demo.app.service.CityService;
import com.hand.demo.infra.mapper.CityMapper;
import com.hand.demo.domain.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * 城市业务逻辑实现类
 */
@Service
public class CityServiceImpl implements CityService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> findAllCity() {
        List<City> list = cityMapper.findAllCity();
        return list;
    }

    @Override
    public City findCityByName(String cityName) {
        return cityMapper.findCityByName(cityName);
    }

    @Override
    public Long saveCity(City city) {
        return cityMapper.saveCity(city);
    }

    /**
     * 获取城市逻辑：使用缓存
     */
    @Override
    public City findCityById(Long id) {
        String key = "city_" + id;
        // 从缓存中获取城市信息
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        // 缓存存在,则从缓存中获取
        if (redisTemplate.hasKey(key)) {
            City city = operations.get(key);
            logger.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >>\n"
                    + city.toString());
            return city;
        }
        // 否则从DB中获取城市信息
        City city = cityMapper.findCityById(id);
        // 再插入缓存,到期时间30秒
        operations.set(key, city, 30, TimeUnit.SECONDS);
        logger.info("CityServiceImpl.findCityById() : 城市插入缓存 >>\n"
                + city.toString());
        return city;
    }

    /**
     * 更新城市逻辑：删除旧缓存
     */
    @Override
    public Long updateCity(City city) {
        Long ret = cityMapper.updateCity(city);
        String key = "city_" + city.getId();
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            logger.info("CityServiceImpl.updateCity() : 从缓存中删除旧城市:"
                    + city.toString());
        }
        return ret;
    }

    /**
     * 删除城市逻辑：删除缓存
     */
    @Override
    public Long deleteCity(Long id) {
        Long ret = cityMapper.deleteCity(id);
        String key = "city_" + id;
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            logger.info("CityServiceImpl.deleteCity() : 从缓存中删除城市,ID:\n" +
                    "" + id);
        }
        return ret;
    }


}
