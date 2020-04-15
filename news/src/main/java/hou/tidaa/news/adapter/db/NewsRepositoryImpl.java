package hou.tidaa.news.adapter.db;

import hou.tidaa.news.domain.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepositoryImpl implements NewsRepository {
    private final NewsPersistence newsPersistence;

    @Autowired
    public NewsRepositoryImpl(NewsPersistence newsPersistence) {
        this.newsPersistence = newsPersistence;
    }

    @Override
    public News getNews(String newsId) {
        return newsPersistence
                .findById(newsId)
                .map(entity -> new News(entity.getId(), entity.getAuthorId(), entity.getTitle(), entity.getBody()))
                .orElse(null);
    }

    @Override
    public News save(News news) {
        final NewsEntity newsEntity = new NewsEntity(news);
        newsPersistence.save(newsEntity);
        return news;
    }
}
