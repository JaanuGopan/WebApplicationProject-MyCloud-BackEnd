package com.project.cloudstoragemanagement.dao;

import org.apache.catalina.User;

import java.util.List;

public interface IUserDAO {
    List<User> findAll();
    User findById(int id);
    User save(User user);
    void deleteById(int id);


}
