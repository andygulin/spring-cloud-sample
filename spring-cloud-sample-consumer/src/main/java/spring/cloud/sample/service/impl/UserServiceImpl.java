package spring.cloud.sample.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.cloud.sample.bean.User;
import spring.cloud.sample.service.UserService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    @Override
    public User getUser(int id) {
        return restTemplate.getForEntity("http://USER-SERVICE/user/get/" + id, User.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @Override
    public List<User> getAll() {
        return restTemplate.getForEntity("http://USER-SERVICE/user/all", List.class).getBody();
    }

    public List<User> fallback() {
        logger.info("出现异常，进入fallback...");
        return null;
    }

    public User fallback(int id) {
        logger.info("出现异常，进入fallback...，参数" + id);
        return null;
    }
}
