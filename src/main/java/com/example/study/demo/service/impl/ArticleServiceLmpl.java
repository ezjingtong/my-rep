package com.example.study.demo.service.impl;

import com.example.study.demo.mapper.ArticleMapper;
import com.example.study.demo.pojo.Category;
import com.example.study.demo.service.ArticleService;
import com.example.study.demo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceLmpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public void addCategory(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        articleMapper.addCategory(category);
    }

    @Override
    public List<Category> getAllCategory(Integer id) {
        List<Category> allCategory = articleMapper.getAllCategory(id);
        return allCategory;
    }

    @Override
    public Category getDetailCategory(Integer id) {
        Category detailCategory = articleMapper.getDetailCategory(id);
        return detailCategory;
    }
}
