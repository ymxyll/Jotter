package com.example.wj.dao;

import com.example.wj.entity.JotterArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JotterArticleDAO extends JpaRepository<JotterArticle, Integer> {
    JotterArticle findById(int id);
}
