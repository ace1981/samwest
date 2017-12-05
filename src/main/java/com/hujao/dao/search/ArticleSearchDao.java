package com.hujao.dao.search;
import com.hujao.model.ArticleModel;
import com.hujao.po.ArticleEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface  ArticleSearchDao extends ElasticsearchRepository<ArticleModel, String>{

}
