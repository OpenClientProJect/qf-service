package com.love.controller;

import com.love.pojo.Tag;
import com.love.pojo.Result;
import com.love.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 发布公告
     */
    @PostMapping
    public Result<?> publish(@RequestBody Tag tag) {
        tagService.publish(tag);
        return Result.success();
    }
    /**
     * 获取公告列表
     */
    @GetMapping("/firstTag")
    public Result<List<Tag>> getFirstTagList() {
        List<Tag> tags = tagService.getFirstTag();
        return Result.success(tags);
    }
    @GetMapping("/lastTag")
    public Result<List<Tag>> getLastTagList() {
        List<Tag> tags = tagService.getLastTag();
        return Result.success(tags);
    }

    /**
     * 删除公告
     */
    @DeleteMapping
    public Result<?> deleteTag(@RequestParam Integer tagId) {
        tagService.deleteTag(tagId);
        return Result.success();
    }

    /**
     * 修改公告
     */
    @PutMapping
    public Result<?> updateTag(@RequestBody Tag tag) {
        tagService.updateTag(tag);
        return Result.success();
    }
}
