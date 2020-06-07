package com.itheima.service.impl;

import com.itheima.dao.ArticleRepository;
import com.itheima.domain.Article;
import com.itheima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void update(Article article) {
        articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public Article findById(int id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        return articleOptional.get();
    }

    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    public Page<Article> findAll(int currentPage, int size) {
        Pageable pageable = PageRequest.of(currentPage, size);
        return articleRepository.findAll(pageable);
    }

    @Override
    public List<Article> findByTitleAndContent(String title, String content) {
        return articleRepository.findByTitleAndContent(title,content);
    }
    @Override
    public List<Article> findByTitleOrContent(String title, String content) {
        return articleRepository.findByTitleOrContent(title,content);
    }
}
