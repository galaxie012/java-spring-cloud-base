package hou.tidaa.news.adapter.db;

import hou.tidaa.news.domain.model.News;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "news")
@Getter
@NoArgsConstructor
public class NewsEntity {
    @Id
    private String id;
    private String authorId;
    private String title;
    private String body;

    public NewsEntity(News news) {
        id = news.getId();
        authorId = news.getAuthorId();
        title = news.getTitle();
        body = news.getBody();
    }
}
