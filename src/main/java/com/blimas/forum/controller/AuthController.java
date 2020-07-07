package com.blimas.forum.controller;

import com.blimas.forum.config.security.TokenService;
import com.blimas.forum.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid LoginForm loginForm){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginForm.getLoginUser();
        try{
            Authentication authenticate = authManager.authenticate(usernamePasswordAuthenticationToken);
            String token = tokenService.generateToken(authenticate);
            System.out.println(token);
            return ResponseEntity.ok().build();
        }catch (AuthenticationException authenticationException){
            return ResponseEntity.badRequest().build();
        }
    }
}
