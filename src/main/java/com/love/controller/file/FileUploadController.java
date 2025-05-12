package com.love.controller.file;

import com.love.pojo.Result;
import com.love.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    // 视频文件上传接口
    @PostMapping("/uploadVideo")
    public Result<String> handleVideoUpload(@RequestParam("video") MultipartFile video) {
        String videoUrl = fileUploadService.uploadVideo(video);
        // 返回统一的响应结果，视频Url地址
        return Result.success(videoUrl);
    }

    //图片上传接口
    @PostMapping("/uploadImage")
    public Result<String> handleImageUpload(@RequestParam("image") MultipartFile file) {
        String imageUrl = fileUploadService.uploadImage(file);
        // 返回统一的响应结果
        return Result.success(imageUrl);

    }
}
