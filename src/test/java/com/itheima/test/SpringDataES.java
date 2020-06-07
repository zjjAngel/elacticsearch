package com.itheima.test;

import com.itheima.domain.Article;
import com.itheima.service.ArticleService;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataES {

    @Autowired
    ArticleService articleService;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;//es模板对象

    //创建索引，配置映射
    @Test
    public void createIndex(){
        elasticsearchTemplate.createIndex(Article.class);//创建索引
        elasticsearchTemplate.putMapping(Article.class);//配置映射
    }
    //创建文档
    @Test
    public void createDoc(){
        Article article = new Article();
        article.setId(1);
        article.setTitle("ElasticSearch是一个基于Lucene的搜索服务器");
        article.setContent("它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开.");
        articleService.save(article);
    }
    //修改文档
    @Test
    public void update(){
        Article article = new Article();
        article.setId(1);
        article.setTitle("【修改】ElasticSearch是一个基于Lucene的搜索服务器");
        article.setContent("【修改】它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。Elasticsearch是用Java开发的，并作为Apache许可条款下的开.");
        articleService.update(article);
    }
    //删除文档
    @Test
    public void del(){
        Article article = new Article();
        article.setId(1);
        articleService.delete(article);
    }
    //基本查询
    //findById
    @Test
    public void findById(){
        Article article =  articleService.findById(1);
        System.out.println(article);
    }
    //findAll
    @Test
    public void findAll(){
        Iterable<Article> articles =  articleService.findAll();
        int count=0;
        for (Article article : articles) {
            System.out.println(article);
            count++;
        }
        System.out.println("总条数:" + count);
    }
    // 分页查询
    @Test
    public void findByPage(){

        int currentPage=0;
        int size = 2;
        Page<Article> articles =  articleService.findAll(currentPage,size);
        for (Article article : articles.getContent()) {
            System.out.println(article);
        }
    }
    //复合查询
    @Test
    public void findByTitleAndContent(){
        String title="搜索";
        String content="xxx";
//        List<Article> articles = articleService.findByTitleAndContent(title,content);
        List<Article> articles = articleService.findByTitleOrContent(title,content);
        for (Article article : articles) {
            System.out.println(article);
        }
    }
    // 高亮查询,term查询和queryString查询
    //原生查询
    @Test
    public void nativeQuery(){

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery("title","搜索"));//设置查询方式
        queryBuilder.withPageable(PageRequest.of(0,5));//设置分页
        //queryBuilder.withHighlightFields()//高亮查询
        List<Article> articles = elasticsearchTemplate.queryForList(queryBuilder.build(), Article.class);

        for (Article article : articles) {
            System.out.println(article);
        }
    }


}
