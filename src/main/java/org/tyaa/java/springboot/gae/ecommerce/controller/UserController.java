/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.java.springboot.gae.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyaa.java.springboot.gae.ecommerce.model.JsonHttpResponse;
import org.tyaa.java.springboot.gae.ecommerce.model.UserModel;
import org.tyaa.java.springboot.gae.ecommerce.service.AuthService;

/**
 *
 * @author yurii
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private AuthService authService;

    @GetMapping("")
    public JsonHttpResponse getAll() {
    
        return authService.readUser();
    }

    @GetMapping(value = "/{id}")
    public JsonHttpResponse get(@PathVariable("id") Long _id) throws Exception {
        
        return authService.readUser(_id);
    }
    
    /*@GetMapping(value = "/get-by-name/{name}")
    public JsonHttpResponse getByName(@PathVariable("name") String _name) throws Exception {
        
        return authorService.read(_name);
    }*/
    
    @PostMapping("/create")
    public JsonHttpResponse create(@RequestBody UserModel _user) throws Exception {
        return authService.createUser(_user);
    }
    
    /*@PostMapping("/update")
    public JsonHttpResponse update(@RequestBody Author _author) {
        return authorService.update(_author);
    }*/
    
    @DeleteMapping(value = "/delete/{id}")
    public JsonHttpResponse delete(@PathVariable("id") Long _id) {
        
        return authService.deleteUser(_id);
    }
}
