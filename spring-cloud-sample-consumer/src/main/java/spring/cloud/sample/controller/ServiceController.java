package spring.cloud.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.sample.client.UserServiceClient;
import spring.cloud.sample.response.MessageResponse;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping("/user/all")
    public MessageResponse all() {
        return MessageResponse.success(userServiceClient.all());
    }

    @GetMapping("/user/{id}")
    public MessageResponse get(@PathVariable("id") int id) {
        return MessageResponse.success(userServiceClient.get(id));
    }
}