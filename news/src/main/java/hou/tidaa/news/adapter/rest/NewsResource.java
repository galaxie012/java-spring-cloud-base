package hou.tidaa.news.adapter.rest;

import hou.tidaa.news.domain.dto.NewsContext;
import hou.tidaa.news.domain.dto.NewsCreateDto;
import hou.tidaa.news.domain.model.News;
import hou.tidaa.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/news", produces = APPLICATION_JSON_VALUE)
public class NewsResource {
    private final NewsService newsService;

    @Autowired
    public NewsResource(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public NewsContext get(@PathVariable String id) {
        return newsService.getNews(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsContext create(@Valid @RequestBody NewsCreateDto request) {
        News news = newsService.createNews(request.authorId, request.title, request.body);
        return newsService.getNews(news.getId());
    }
}
