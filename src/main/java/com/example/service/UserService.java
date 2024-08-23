package com.example.service;

import com.example.pojo.User;

public interface UserService {

    User findByUsername( String username);
    void register(String username, String password);

    void update(User user);

    void updateavatar(String userPicUrl);

    void updatemima(String newpwd);
}
