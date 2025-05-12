package com.love.service.impl.anime;

import com.love.mapper.anime.AnimeEpisodeMapper;
import com.love.pojo.AnimeEpisode;
import com.love.service.AnimeEpisodeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeEpisodeServiceImpl implements AnimeEpisodeService {

    @Resource
    AnimeEpisodeMapper animeEpisodeMapper;

    @Override
    public List<AnimeEpisode> list(Long id) {
        return animeEpisodeMapper.list(id);
    }
}
