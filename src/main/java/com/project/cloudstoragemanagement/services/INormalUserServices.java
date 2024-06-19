package com.project.cloudstoragemanagement.services;

import com.project.cloudstoragemanagement.entity.NormalUser;

import java.util.List;
public interface INormalUserServices {

    List<NormalUser> findAllNormalUser();
    NormalUser findByNormalUserId(int id);
    NormalUser saveNormalUser(NormalUser normalUser);
    void deleteByNormalUserId(int id);
}
