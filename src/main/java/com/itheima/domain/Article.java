package com.itheima.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

//配置文档基本信息，索引名称，类型名称
@Document(indexName = "blog3",type = "article")
public class Article {
    /**
     *@Id，代表把当前id与索引库类型的主键_id进行绑定
     * @Field()配置当前的字段
     */
    @Id
    @Field(type = FieldType.Integer,store = true,index = false)
    private Integer id;
    @Field(type = FieldType.text,store = true,index = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String title;
    @Field(type = FieldType.text,store = true,index = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String content;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
