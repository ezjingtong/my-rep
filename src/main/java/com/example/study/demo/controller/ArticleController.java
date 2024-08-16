package com.example.study.demo.controller;
import com.example.study.demo.pojo.Category;
import com.example.study.demo.pojo.Result;
import com.example.study.demo.service.ArticleService;
import com.example.study.demo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @PostMapping("/category")
    public Result addCategory(@RequestBody Category category) {
        articleService.addCategory(category);
        return Result.success();
    }

    @GetMapping("/category")
    public Result<List<Category>> getCategory(){
        Map map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        List<Category> allCategory = articleService.getAllCategory(id);
        return Result.success(allCategory);
    }

    @GetMapping("/category/detail")
    public Result<Category> getDetailCategory(Integer id){
        Category detailCategory = articleService.getDetailCategory(id);
        return Result.success(detailCategory);
    }
}
