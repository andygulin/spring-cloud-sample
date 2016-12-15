package spring.cloud.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import spring.cloud.sample.bean.User;
import spring.cloud.sample.service.UserService;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM user", BeanPropertyRowMapper.newInstance(User.class));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public User getUser(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", BeanPropertyRowMapper.newInstance(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            return new User();
        }
    }
}
