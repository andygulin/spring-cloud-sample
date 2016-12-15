package spring.cloud.sample.service;

import spring.cloud.sample.bean.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUser(int id);
}
