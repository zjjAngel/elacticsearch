package com.itheima.dao;

import com.itheima.domain.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * ElasticsearchRepository<Article,Integer>
 * 泛型两个参数：
 * 第一个参数是当前实体类
 * 第二个参数是实体类的主键类型
 */
public interface ArticleRepository extends ElasticsearchRepository<Article,Integer> {

    //select * form article where title=xx and content = xxx
    List<Article> findByTitleAndContent(String title,String content);

    List<Article> findByTitleOrContent(String title, String content);
}
