package com.example.wj.service;

import com.example.wj.dao.JotterArticleDAO;
import com.example.wj.entity.JotterArticle;
import com.example.wj.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class JotterArticleService {
    @Autowired
    JotterArticleDAO jotterArticleDAO;

    public MyPage list(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");//按照id对查询结果排序
        Page<JotterArticle> articlesInDb = jotterArticleDAO.findAll(PageRequest.of(page, size, sort));
        return new MyPage<>(articlesInDb);
    }

    public JotterArticle findById(int id) {
        return jotterArticleDAO.findById(id);
    }

    public void addOrUpdate(JotterArticle article) {
        System.out.println(article.getArticleDate());
        jotterArticleDAO.save(article);
    }

    public void delete(int id) {
        jotterArticleDAO.deleteById(id);
    }
}
