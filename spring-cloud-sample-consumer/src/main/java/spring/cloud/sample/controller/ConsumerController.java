package spring.cloud.sample.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import spring.cloud.sample.bean.User;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private static final Logger logger = Logger.getLogger(ConsumerController.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/user/all")
    public List<User> all() {
        return restTemplate.getForEntity("http://user-service/user/all", List.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/user/get/{id}")
    public User get(@PathVariable("id") int id) {
        return restTemplate.getForEntity("http://user-service/user/get/" + id, User.class).getBody();
    }

    public List<User> fallback(Throwable e) {
        logger.info("出现异常，进入fallback...");
        e.printStackTrace();
        return null;
    }

    public User fallback(int id,Throwable e) {
        logger.info("出现异常，进入fallback...，参数" + id);
        e.printStackTrace();
        return null;
    }
}
