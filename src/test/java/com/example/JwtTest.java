package com.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.auth0.jwt.JWT.require;

public class JwtTest{
    @Test
     //生成jwt
    public void testout(){
        Map<String, Object> claims = new HashMap<>();  //新建HashMap
        claims.put("id",1);
        claims.put("username","zhangsan");
      String token=JWT.create()
                 .withClaim("user",claims)
                 .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60))
                 .sign(Algorithm.HMAC256("Jason"));
         System.out.println(token);

     }

     @Test
    public void testinput(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJleHAiOjE3MjQzMDE3MjIsInVzZXIiOnsiaWQiOjEsInVzZXJuYW1lIjoiemhhbmdzYW4ifX0" +
                ".MJ0CVaR17S4z6D2t_-txdvbembZCigjtREXYlGdtAqo";
       JWTVerifier jwtv=JWT.require(Algorithm.HMAC256("Jason")).build();
         DecodedJWT deco=jwtv.verify(token);
         Map<String, Claim> cals=deco.getClaims();
         System.out.println(cals.get("user"));
     }


}


