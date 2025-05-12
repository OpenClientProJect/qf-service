package com.love.controller.anime;

import com.love.pojo.Anime;
import com.love.pojo.Result;
import com.love.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnimeCarouselController {

    @Autowired
    AnimeService animeService;

    //anime轮播图
    @GetMapping("/Carousel")
    public Result<List<Anime>> Carousel() {
        List<Anime> anime = animeService.Carousel();
        return Result.success(anime);
    }
}
