package com.love.controller.admin;

import com.love.pojo.HomeImage;
import com.love.pojo.Result;
import com.love.service.admin.AdminHomeImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/homeImage")
public class HomeImageController {

    @Autowired
    private AdminHomeImageService adminHomeImageService;

    @PostMapping("/upload")
    public Result<?> upload(@RequestBody HomeImage homeImage) {
        adminHomeImageService.add(homeImage);
        return Result.success();
    }

    @GetMapping("/image")
    public Result<List<HomeImage>> getHomeImageList() {
        List<HomeImage> homeImageList = adminHomeImageService.getHomeImageList();
        return Result.success(homeImageList);
    }
    //编辑
    @PutMapping
    public Result<?> update(@RequestBody HomeImage homeImage) {
        adminHomeImageService.update(homeImage);
        return Result.success();
    }
    //背景图
    @GetMapping("/background")
    public Result<Map<String,Object>> getBackground() {
        Map<String,Object> background = adminHomeImageService.getBackground();
        return Result.success(background);
    }

    @PutMapping("/background")
    public Result<?> updateBackground(@RequestBody Map<String,Object> map) {
        Integer id = (Integer) map.get("id");
        String img = (String) map.get("img");
        adminHomeImageService.updateBackground(id, img);
        return Result.success();
    }

    @PostMapping("/background")
    public Result<?> addBackground(@RequestBody Map<String,Object> map) {
        String img = (String) map.get("img");
        adminHomeImageService.addBackground(img);
        return Result.success();
    }

//    //删除
//    @DeleteMapping("/{homeImgId}")
//    public Result<?> delete(@PathVariable("homeImgId") Integer homeImgId) {
//        adminHomeImageService.delete(homeImgId);
//        return Result.success();
//    }
}
