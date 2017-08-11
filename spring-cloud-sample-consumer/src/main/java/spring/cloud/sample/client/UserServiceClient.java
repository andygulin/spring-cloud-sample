package spring.cloud.sample.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.cloud.sample.bean.User;

import java.util.List;

@FeignClient(name = "user-service", path = "/user")
public interface UserServiceClient {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<User> all();

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    User get(@PathVariable("id") int id);
}