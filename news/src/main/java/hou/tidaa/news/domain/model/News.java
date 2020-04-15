package hou.tidaa.news.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class News {
    private final String id;
    private final String authorId;
    private final String title;
    private final String body;

    public News(String authorId, String title, String body) {
        id = UUID.randomUUID().toString();
        this.authorId = authorId;
        this.title = title;
        this.body = body;
    }
}
