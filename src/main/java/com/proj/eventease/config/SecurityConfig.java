package com.proj.eventease.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.web.SecurityFilterChain;

import com.proj.eventease.services.impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
//import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    //user create and login using  java code in memory service

    // @Bean
    // public UserDetailsService userDetailsService(){

    //     UserDetails user =User.withDefaultPasswordEncoder().username("admin123").password("admin123").roles("ADMIN","USER").build();
    //     var inMemoryUserDetailsManager =new InMemoryUserDetailsManager(user);
    //     return inMemoryUserDetailsManager;
    // } 

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
    //         .csrf(csrf -> csrf.disable())
    //         .formLogin(login -> login.disable())
    //         .httpBasic(basic -> basic.disable());

    //     return http.build();
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    //config of authentication provider for spring security
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider =new DaoAuthenticationProvider();
        //user detail service ka object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        //password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    //
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //config 
        //urls ko configure which ones to stay public or protected
        httpSecurity.authorizeHttpRequests(authorize->{
            //authorize.requestMatchers("/home","/register","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        //form default login
        //form login s related if we need to change anythign we will come here
        httpSecurity.formLogin(formLogin->{
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/dashboard");
            //formLogin.failureForwardUrl(("/login?error=true"));
            formLogin.usernameParameter("email");
            formLogin.passwordParameter(("password"));
            //formLogin.failureHandler(new AuthenticationFailureHandler() {

        //         @Override
        //         public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        //                 AuthenticationException exception) throws IOException, ServletException {
        //             // TODO Auto-generated method stub
        //             throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
        //         }
                
        //     });

        //     formLogin.successHandler(new AuthenticationSuccessHandler() {

        //         @Override
        //         public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        //                 Authentication authentication) throws IOException, ServletException {
        //             // TODO Auto-generated method stub
        //             throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
        //         }
        //     });
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
