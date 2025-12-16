package com.pharm.track.service;

import com.pharm.track.dtos.UserDto;
import com.pharm.track.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    /** 🔹 회원가입 */
    public void register(UserDto user) {

        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // 비번 암호화
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);

        // phone, pharmacyName은 그대로 userDto에 담겨있음
        userMapper.insertUser(user);
    }

    /** 🔹 로그인 */
    public UserDto login(String username, String password) {
        UserDto user = userMapper.findByUsername(username);
        if (user == null) return null;

        if (!BCrypt.checkpw(password, user.getPassword())) return null;

        // 비밀번호 제외 반환
        UserDto safeUser = new UserDto();
        safeUser.setUserId(user.getUserId());
        safeUser.setUsername(user.getUsername());
        safeUser.setName(user.getName());
        safeUser.setRole(user.getRole());
        safeUser.setPhone(user.getPhone());
        safeUser.setPharmacyName(user.getPharmacyName());

        return safeUser;
    }

    public UserDto findById(Long id) {
        return userMapper.findById(id);
    }
    
    public UserDto getUserById(Long id) {
        return userMapper.findById(id);
    }

    public void updateUser(Long id, UserDto dto) {
        // 비밀번호는 null 또는 "" 이면 변경하지 않음
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            dto.setPassword(null); // Mapper에서 null이면 SET하지 않도록 처리
        }

        dto.setUserId(id);
        userMapper.update(dto);
    }

}
