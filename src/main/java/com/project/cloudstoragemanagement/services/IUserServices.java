package com.project.cloudstoragemanagement.services;

import org.apache.catalina.User;

import java.util.List;

public interface IUserServices {
    List<User> findAll();
    User findById(int id);
    User save(User user);
    void deleteById(int id);
}
