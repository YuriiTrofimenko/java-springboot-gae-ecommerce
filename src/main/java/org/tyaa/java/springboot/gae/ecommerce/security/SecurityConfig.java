/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.springboot.gae.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author yurii
 */
@Configuration
@EnableWebSecurity
@Order(1)
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private ObjectifyWebAuthProvider authProvider;
    
    /*@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
    }*/
    
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    
    @Autowired
    private SavedReqAwareAuthSuccessHandler savedReqAwareAuthSuccessHandler;
    
    /* @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    } */

    //@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
    
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        // .formLogin().disable()
        // .headers().frameOptions().disable().and()
        .exceptionHandling()
        .authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .authorizeRequests()
        //.anyRequest().permitAll()
        .antMatchers("/api/user/**").authenticated()
        .antMatchers("/api/admin/**").hasRole("admin")
        .and()
        .formLogin()//.loginPage("/login").permitAll()
        .successHandler(savedReqAwareAuthSuccessHandler)
        .and()
        .logout();
        // .failureHandler(myFailureHandler)
        //.and()
        //.logout().permitAll();
    }
    
    
}
