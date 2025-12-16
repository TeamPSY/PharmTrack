package com.pharm.track.model;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String username;
    private String password;
    private String name;  // 실명
    private String phone;
    private String pharmacyName;   // 약국명

}
