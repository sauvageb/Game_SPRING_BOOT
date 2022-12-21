package com.training.games.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    public String generateJwt(Authentication authentication) {
        return "toto";
    }

    public boolean validateJwt(String token){
        return token.equals("toto");
    }



}
