package com.example.controller;

import com.auth0.jwt.JWTVerifier;
import com.example.pojo.Result;
import com.example.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result list(){
         return Result.success("成功获取文章数据");

    }
}
