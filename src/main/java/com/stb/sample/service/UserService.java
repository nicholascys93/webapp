package com.stb.sample.service;

import com.stb.sample.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
