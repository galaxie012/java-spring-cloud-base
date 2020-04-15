package hou.tidaa.news.domain.dto;

import javax.validation.constraints.NotNull;

public class NewsCreateDto {
    @NotNull
    public String authorId;
    @NotNull
    public String title;
    @NotNull
    public String body;
}
