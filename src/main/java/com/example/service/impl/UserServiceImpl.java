package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.utils.Md5Util;
import com.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private  UserMapper userMapper ;
    @Override
    public User findByUsername(String username) {
        User u=userMapper.findByUsername(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
    String md5tring= Md5Util.getMD5String(password);
        userMapper.add(username,md5tring);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        System.out.println("Updating user: " + user);
        userMapper.update(user);
    }

    @Override
    public void updateavatar(String userPicUrl) {
        Map<String, Object> map= ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        userMapper.updateavatar(userPicUrl,id);
    }

    @Override
    public void updatemima(String newpwd) {
        Map<String, Object> userClaims = ThreadLocalUtil.get();
        Integer id = (Integer) userClaims.get("id");

        userMapper.updatemima(Md5Util.getMD5String(newpwd), id);

    }
}
