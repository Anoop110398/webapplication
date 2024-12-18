package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.model.User;

@Service
public class UserService {
	
	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findUserByUserid(String userid) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{userid}, (rs, rowNum) -> {
            User user = new User();
            user.setUserid(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        });
        return users.isEmpty() ? null : users.get(0);
    }



}
