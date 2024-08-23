package com.example.controller;

import com.example.pojo.Result;
import com.example.pojo.User;
import com.example.utils.JwtUtil;
import com.example.utils.Md5Util;
import com.example.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.ObjIntConsumer;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userservice;

    @PostMapping("/register")
    public Result register(@Pattern(regexp ="^\\S{5,16}$")  String username, @Pattern(regexp ="^\\S{5,16}$") String password )
    {
  
        User u=userservice.findByUsername(username);
        if(u==null) {
              userservice.register(username,password);
              return Result.success();
        }
        else
        {
            return Result.error("用户名已经存在");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp ="^\\S{5,16}$")  String username,@Pattern(regexp ="^\\S{5,16}$") String password)
    {
        User loginuser=userservice.findByUsername(username);
        if (loginuser == null) {
            return Result.error("该用户不存在");

        }
        if(Md5Util.getMD5String(password).equals(loginuser.getPassword())){

            Map<String,Object> claims=new HashMap<>();
//            claims.put(String.valueOf(loginuser.getId()),loginuser.getUsername());
//
   /*
   token存取的只有id和username
    */
            claims.put("id",loginuser.getId());
            claims.put("username",loginuser.getUsername());
            String token=JwtUtil.genToken(claims);
            return Result.success(token);
        }
        else {
            return Result.error("密码错误");
        }

    }
    @GetMapping("/userinfo")
    public Result userinfo(@RequestHeader("Authorization") String token) {


        Map<String, Object> claims= ThreadLocalUtil.get();
        String relname=(String)claims.get("username");
        User user=userservice.findByUsername(relname);
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result update(@RequestBody  @Validated User user) {

        userservice.update(user);
        return Result.success();

    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam  @URL String userPicUrl) {
        userservice.updateavatar(userPicUrl);
        return Result.success();

    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> parms  ) {
        String oldpwd=parms.get("old_pwd");
        String newpwd=parms.get("new_pwd");
        String repwd=parms.get("re_pwd");
         if(!StringUtils.hasLength(oldpwd)||!StringUtils.hasLength(newpwd)||!StringUtils.hasLength(repwd)) {
             return Result.error("缺少必要的参数");
         }
         Map<String,Object> map= ThreadLocalUtil.get();

         User user=userservice.findByUsername((String)map.get("username"));
        String trueold_pwd=(String) user.getPassword();
       if(!trueold_pwd.equals(Md5Util.getMD5String(oldpwd))){
           return Result.error("原密码输入错误");
       }
       if(!newpwd.equals(repwd)){
           return Result.error("两次密码输入不一致");}

        userservice.updatemima(newpwd);
        return Result.success();

    }


}
