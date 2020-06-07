package com.itheima.service;

import com.itheima.domain.Article;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    void save(Article article);

    void update(Article article);

    void delete(Article article);

    Article findById(int i);

    Iterable<Article> findAll();

    Page<Article> findAll(int currentPage, int size);

    List<Article> findByTitleAndContent(String title, String content);

    List<Article> findByTitleOrContent(String title, String content);
}
