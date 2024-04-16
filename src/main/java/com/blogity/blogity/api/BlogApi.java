package com.blogity.blogity.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogity.blogity.dto.BlogDto;
import com.blogity.blogity.service.BlogService;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogApi {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<BlogDto> getAllBlogs(){
        return blogService.getAllBlogs();
    }

    @PostMapping
    public BlogDto setNewBlog(@RequestBody BlogDto newBlog){
        return blogService.setNewBlog(newBlog);
    }

    @PutMapping("/{id}")
    public BlogDto updateExistingBlog(@PathVariable(name = "id") String id,@RequestBody BlogDto updateData){
        return blogService.updateExistingBlog(id,updateData);
    }

    @DeleteMapping("/{id}")
    public String deleteExistingBlog(@PathVariable(name = "id") String id){
        return blogService.deleteExistingBlog(id);
    }
}
