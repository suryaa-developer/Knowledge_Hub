package com.PKH.Hub.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class BCryptUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
               auth ->auth.anyRequest().permitAll()).
               httpBasic(httpbasic ->{});

         return http.build(); }
    /*
     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
               auth ->auth.requestMatchers("/signup","/login").
                       permitAll().anyRequest().authenticated()).
               httpBasic(httpbasic ->{});

         return http.build(); }
     */

  public PasswordEncoder PasswordEncoder(){
      return new BCryptPasswordEncoder();
  }



    public String hashPassword(String plainPassword) {

      return encoder.encode(plainPassword);
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }

}

