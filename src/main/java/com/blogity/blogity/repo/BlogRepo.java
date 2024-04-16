package com.blogity.blogity.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.blogity.blogity.modal.Blog;

public interface BlogRepo extends MongoRepository<Blog,String>{

}
