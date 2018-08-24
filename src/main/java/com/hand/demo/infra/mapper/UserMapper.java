package com.hand.demo.infra.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    List<Integer> login(Map<String, Object> map);
}
