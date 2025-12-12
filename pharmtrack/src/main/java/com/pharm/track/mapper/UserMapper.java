package com.pharm.track.mapper;

import com.pharm.track.dtos.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertUser(UserDto user);
    
    void update(UserDto user);

    UserDto findByUsername(String username);

    UserDto findById(Long id);
}
