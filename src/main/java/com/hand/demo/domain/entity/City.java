package com.hand.demo.domain.entity;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 城市实体类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;
    private Long provinceId = 0L;
    private String cityName;
    private String description = "Empty";

    public City(Long provinceId, String cityName) {
        this.provinceId = provinceId;
        this.cityName = cityName;
    }

    public City(Long id, Long provinceId, String cityName, String description) {
        this.id = id;
        this.provinceId = provinceId;
        this.cityName = cityName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", provinceId=" + provinceId +
                ", cityName='" + cityName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
