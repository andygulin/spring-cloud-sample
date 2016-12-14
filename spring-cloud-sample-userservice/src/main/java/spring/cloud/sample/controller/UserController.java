package spring.cloud.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.sample.bean.User;
import spring.cloud.sample.service.UserService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> all() {
        return userService.getAll();
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") int id) {
        return userService.getUser(id);
    }
}
