package com.example.wj.controller;

import com.example.wj.entity.JotterArticle;
import com.example.wj.result.Result;
import com.example.wj.result.ResultFactory;
import com.example.wj.service.JotterArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class JotterController {
    @Autowired
    JotterArticleService jotterArticleService;

    @PostMapping("api/admin/content/article")//@Valid进行数据校验
    public Result saveArticle(@RequestBody @Valid JotterArticle article) {
        jotterArticleService.addOrUpdate(article);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @GetMapping("/api/article/{size}/{page}")
    public Result listArticles(@PathVariable("size") int size, @PathVariable("page") int page) {
        return ResultFactory.buildSuccessResult(jotterArticleService.list(page - 1, size));
    }

    @GetMapping("/api/article/{id}")
    public Result getOneArticle(@PathVariable("id") int id) {   //这里id一定是存在对应文章的
        return ResultFactory.buildSuccessResult(jotterArticleService.findById(id));
    }

    @DeleteMapping("/api/admin/content/article/{id}")
    public Result deleteArticle(@PathVariable("id") int id) {
        jotterArticleService.delete(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}
