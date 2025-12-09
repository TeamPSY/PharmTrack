package com.pharm.track.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String password;
    private String name;
    private String role;
    private String phone;
    private String pharmacyName;

}
