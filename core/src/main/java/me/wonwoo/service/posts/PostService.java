package me.wonwoo.service.posts;

import me.wonwoo.domain.posts.Posts;
import me.wonwoo.exception.IdNotFoundException;
import me.wonwoo.repository.posts.PostRepository;
import me.wonwoo.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Posts findPost(Long id){
    return getPost(id);
  }

  public Posts save(Posts posts){
    return postRepository.save(posts);
  }

  public Posts update(Long id, Posts posts){
    Posts oldPosts = getPost(id);
    if (oldPosts == null) {
      throw new IdNotFoundException(id);
    }
    ReflectionUtils.oldInstanceBynewInstance(oldPosts, posts);
    return oldPosts;
  }

  public void delete(Long id){
    getPost(id);
    postRepository.delete(id);
  }

  private Posts getPost(Long id) {
    Posts post = postRepository.findOne(id);
    if(post == null){
      throw new IdNotFoundException(id);
    }
    return post;
  }
}
