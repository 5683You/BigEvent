package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
  @Insert("insert into user( username, password, createtime, updatetime)"+" values(#{username},#{password},now(),now())")
   void add(String username, String password);
     @Select("select * from user where username=#{username}")
     User findByUsername(String username) ;
    @Update("update user set nickname=#{nickname},email=#{email},updateTime=#{updateTime} where id=#{id}")
    void update(User user);



    @Update("update user set userPic=#{userPicUrl},updateTime=now() where id=#{id}")
    void updateavatar(String userPicUrl, Integer id);

    @Update("update user set password=#{newpwd},updateTime=now() where id=#{id}")
    void updatemima(String newpwd,Integer id);
}
