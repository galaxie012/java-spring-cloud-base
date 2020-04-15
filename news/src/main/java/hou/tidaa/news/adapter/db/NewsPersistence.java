package hou.tidaa.news.adapter.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsPersistence extends CrudRepository<NewsEntity, String> {
}
