package me.wonwoo.controller.posts;

import lombok.extern.slf4j.Slf4j;
import me.wonwoo.config.annotation.RestControllerOk;
import me.wonwoo.domain.posts.Posts;
import me.wonwoo.exception.BadRequestException;
import me.wonwoo.repository.posts.PostRepository;
import me.wonwoo.service.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.response.success.ResponseStatusCreated;
import org.springframework.http.response.success.ResponseStatusNoContent;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@RestControllerOk
@Slf4j
public class PostController {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private PostService postService;

  @GetMapping("/posts")
  public Page<Posts> findPosts(Pageable pageable){
    return postRepository.findAll(pageable);
  }

  @GetMapping("/posts/{id}")
  public Posts findPost(@PathVariable Long id){
    return postService.findPost(id);
  }

  @PostMapping("/posts")
  @ResponseStatusCreated
  public Posts save(@Valid @RequestBody Posts posts, BindingResult bs){
    if(bs.hasErrors()){
      throw new BadRequestException(bs.getFieldError().getDefaultMessage());
    }
    return postService.save(posts);
  }

  @PatchMapping("/posts/{id}")
  public Posts update(@PathVariable Long id, @Valid @RequestBody Posts posts, BindingResult bs) {
    if(bs.hasErrors()){
      throw new BadRequestException(bs.getFieldError().getDefaultMessage());
    }
    return postService.update(id,posts);
  }
  @DeleteMapping("/posts/{id}")
  @ResponseStatusNoContent
  public void delete(@PathVariable Long id){
    postService.delete(id);
  }
}
