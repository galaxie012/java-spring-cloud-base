package hou.tidaa.news.service;

import hou.tidaa.news.adapter.db.NewsRepository;
import hou.tidaa.news.adapter.user.UserService;
import hou.tidaa.news.domain.dto.NewsContext;
import hou.tidaa.news.domain.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final UserService userService;

    @Autowired
    public NewsService(NewsRepository newsRepository, UserService userService) {
        this.newsRepository = newsRepository;
        this.userService = userService;
    }

    public NewsContext getNews(String newsId) {
        News news = newsRepository.getNews(newsId);
        return new NewsContext(
                news.getId(),
                userService.getUsername(news.getAuthorId()),
                news.getTitle(), news.getBody());
    }

    public News createNews(String authorId, String newsname, String password) {
        final News news = new News(authorId, newsname, password);
        newsRepository.save(news);
        return news;
    }
}
