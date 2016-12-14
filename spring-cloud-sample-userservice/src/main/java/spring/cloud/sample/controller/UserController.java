package spring.cloud.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.sample.bean.User;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public List<User> all() {
        printLog();
        return jdbcTemplate.queryForList("SELECT * FROM user", User.class);
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") int id) {
        printLog();
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", BeanPropertyRowMapper.newInstance(User.class), id);
    }

    private void printLog() {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        logger.info("Host : " + instance.getHost());
        logger.info("Port : " + instance.getPort());
        logger.info("Meatdata : " + instance.getMetadata());
        logger.info("ServiceId : " + instance.getServiceId());
        logger.info("Uri : " + instance.getUri());
    }
}
