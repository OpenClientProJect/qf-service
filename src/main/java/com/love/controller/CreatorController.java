package com.love.controller;

import com.love.pojo.Result;
import com.love.service.admin.ManagementUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/creator")
public class CreatorController {

    @Autowired
    private ManagementUserService managementUserService;

}
