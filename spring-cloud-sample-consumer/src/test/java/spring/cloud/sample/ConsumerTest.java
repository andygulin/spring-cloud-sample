package spring.cloud.sample;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import spring.cloud.sample.bean.User;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ConsumerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void all() {
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) restTemplate.getForEntity("http://user-service/user/all", List.class).getBody();
        System.out.println(StringUtils.repeat("=", 120));
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println(StringUtils.repeat("=", 120));
    }

    @Test
    public void get() {
        final int id = 1;
        User user = restTemplate.getForEntity("http://user-service/user/get/" + id, User.class).getBody();
        System.out.println(StringUtils.repeat("=", 120));
        System.out.println(user);
        System.out.println(StringUtils.repeat("=", 120));
    }
}