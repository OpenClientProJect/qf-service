package com.love.service;

import com.love.pojo.Barrage;

import java.util.List;

public interface BarrageService {
    // 发送弹幕
    String sendBarrage(Barrage barrage);
    // 获取弹幕
    List<Barrage> getBarrage(Integer videoId);
}
