package hou.tidaa.news.service;

import hou.tidaa.news.adapter.db.NewsRepository;
import hou.tidaa.news.adapter.user.UserRestClient;
import hou.tidaa.news.domain.dto.NewsContext;
import hou.tidaa.news.domain.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final UserRestClient userRestClient;

    @Autowired
    public NewsService(NewsRepository newsRepository, UserRestClient userRestClient) {
        this.newsRepository = newsRepository;
        this.userRestClient = userRestClient;
    }

    public NewsContext getNews(String newsId) {
        News news = newsRepository.getNews(newsId);
        return new NewsContext(
                news.getId(),
                userRestClient.getUsername(news.getAuthorId()),
                news.getTitle(), news.getBody());
    }

    public News createNews(String authorId, String newsName, String password) {
        final News news = new News(authorId, newsName, password);
        newsRepository.save(news);
        return news;
    }
}
