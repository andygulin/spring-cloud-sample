package spring.cloud.sample.service;

import spring.cloud.sample.bean.User;

import java.util.List;

public interface UserService {

    User getUser(int id);

    List<User> getAll();
}
