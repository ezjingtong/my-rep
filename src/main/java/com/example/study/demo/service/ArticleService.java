package com.example.study.demo.service;

import com.example.study.demo.pojo.Category;

import java.util.List;

public interface ArticleService {
    void addCategory(Category category);

    List<Category> getAllCategory(Integer id);

    Category getDetailCategory(Integer id);
}
