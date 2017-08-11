package spring.cloud.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.sample.bean.User;
import spring.cloud.sample.client.UserServiceClient;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping("/user/all")
    public List<User> all() {
        return userServiceClient.all();
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable("id") int id) {
        return userServiceClient.get(id);
    }
}