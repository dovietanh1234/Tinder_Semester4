package com.semester.tinder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUser {

    @GetMapping("/user/product")
    public ResponseEntity<?> getDataUser(){
        return ResponseEntity.ok("get data of user");
    }

    @GetMapping("/staff/product")
    public ResponseEntity<?> getDataStaff(){
        return ResponseEntity.ok("get data of staff");
    }

    @GetMapping("/admin/product")
    public ResponseEntity<?> getDataAdmin(){
        return ResponseEntity.ok("get data of admin");
    }



}
