package com.project.cloudstoragemanagement.dao;

import com.project.cloudstoragemanagement.entity.NormalUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INormalUserDAO {
    List<NormalUser> findAllNormalUser();

    NormalUser findByNormalUserId(int id);

    NormalUser saveNormalUser(NormalUser normalUser);

    void deleteByNormalUserId(int id);
}