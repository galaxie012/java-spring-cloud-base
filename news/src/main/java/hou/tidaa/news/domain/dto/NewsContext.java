package hou.tidaa.news.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewsContext {
    private String id;
    private String author;
    private String title;
    private String body;
}
