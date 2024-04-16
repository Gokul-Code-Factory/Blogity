package com.blogity.blogity.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogity.blogity.dto.BlogDto;
import com.blogity.blogity.modal.Blog;
import com.blogity.blogity.repo.BlogRepo;

@Service
public class BlogService {
    @Autowired
    private BlogRepo blogRepo;

    private final ModelMapper modelMapper;

    public BlogService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<BlogDto> getAllBlogs() {
        List<Blog> blogs = blogRepo.findAll();
        List<BlogDto> blogDtos = blogs.stream().map(blog -> modelMapper.map(blog, BlogDto.class)).collect(Collectors.toList());
        return blogDtos;
    }

    public BlogDto setNewBlog(BlogDto newBlog) {
        Blog blog = modelMapper.map(newBlog, Blog.class);
        Blog savedBlog = blogRepo.save(blog);
        BlogDto saveBlogDto = modelMapper.map(savedBlog,BlogDto.class);
        return saveBlogDto;
    }

    public BlogDto updateExistingBlog(String id,BlogDto updateData) {
        if(blogRepo.existsById(id)){
            Blog blogWithNewData = modelMapper.map(updateData,Blog.class);
            blogWithNewData.setId(id);
            Blog updatedBlog = blogRepo.save(blogWithNewData);
            return modelMapper.map(updatedBlog, BlogDto.class);
        }
        return setNewBlog(updateData);
    }

    public String deleteExistingBlog(String id) {
        if(blogRepo.existsById(id)){
            blogRepo.deleteById(id);
            return "document deleted successfully";
        }
        return "document not found";
    }

}
