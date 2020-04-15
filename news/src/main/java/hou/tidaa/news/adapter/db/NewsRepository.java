package hou.tidaa.news.adapter.db;

import hou.tidaa.news.domain.model.News;

public interface NewsRepository {
    News getNews(String newsId);

    News save(News news);
}
