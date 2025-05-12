package com.love.controller;

import com.love.anno.Log;
import com.love.pojo.Barrage;
import com.love.pojo.Result;
import com.love.service.BarrageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/barrage")
public class BarrageController {

    @Autowired
    BarrageService barrageService;

    //发送弹幕
    @Log
    @PostMapping("/send")
    public Result<String> sendBarrage(@RequestBody Barrage barrage) {
        return Result.success(barrageService.sendBarrage(barrage));
    }

}
