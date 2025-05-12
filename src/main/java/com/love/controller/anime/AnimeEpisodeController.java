package com.love.controller.anime;

import com.love.dto.AnimeDto;
import com.love.pojo.AnimeEpisode;
import com.love.pojo.Result;
import com.love.service.AnimeService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/anime/episode")
public class AnimeEpisodeController {

    @Resource
    AnimeService animeService;

    //番剧详细信息
    @NotEmpty
    @GetMapping("/{animeId}")
    private Result<AnimeDto> findById(@PathVariable("animeId") @NotEmpty Long id){
        AnimeDto episode = animeService.findEpisode(id);
        return Result.success(episode);
    }
    ///发布动漫剧集
    @NotEmpty
    @PostMapping("/{animeId}")
    private Result<String> publishEpisode(@PathVariable("animeId") @NotEmpty Long animeId, @RequestBody AnimeEpisode episode){
        episode.setAnimeId(animeId);
        return (animeService.publishEpisode(episode));
    }
}
