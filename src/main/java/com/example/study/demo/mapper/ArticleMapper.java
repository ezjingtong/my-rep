package com.example.study.demo.mapper;

import com.example.study.demo.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {



    @Insert("INSERT INTO category (category_name, category_alias, create_user, create_time, update_time) VALUES (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void addCategory(Category category);


    @Select("SELECT * FROM category WHERE create_user=#{createUser}")
    List<Category> getAllCategory(Integer id);

    @Select("select * from category where id=#{id}")
    Category getDetailCategory(Integer id);
}
