package me.wonwoo.controller.posts;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.Application;
import me.wonwoo.domain.posts.enumerated.CommentStatus;
import me.wonwoo.domain.posts.enumerated.PostStatus;
import me.wonwoo.domain.posts.Posts;
import me.wonwoo.domain.posts.enumerated.PostType;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostControllerTest {

  @Autowired
  private WebApplicationContext wc;

  MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
  }

  @Test
  public void a_createPosts() throws Exception {
    Posts postsDomain = getPostsDomain();
    createUser(postsDomain);
  }

  @Test
  public void b_getPosts() throws Exception {
    Posts postsDomain = getPostsDomain();
    createUser(postsDomain);

    mockMvc.perform(get("/posts"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.content.[0].content", is("post content")))
      .andExpect(jsonPath("$.content.[0].postStatus", is("PUBLISH")))
      .andExpect(jsonPath("$.content.[0].postType", is("POST")))
      .andExpect(jsonPath("$.content.[0].commentStatus", is("OPEN")))
      .andExpect(jsonPath("$.content.[0].title", is("title")));
  }

  @Test
  public void c_getPost() throws Exception {
    Posts postsDomain = getPostsDomain();
    createUser(postsDomain);
    mockMvc.perform(get("/posts/{id}", 3))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.content", is("post content")))
      .andExpect(jsonPath("$.title", is("title")));
  }

  @Test
  public void d_patchPost() throws Exception {
    Posts postsDomain = getPostsDomain();
    createUser(postsDomain);

    Posts posts = new Posts();
    posts.setContent("update content");

    mockMvc.perform(patch("/posts/{id}", 4).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(posts)))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.content", is("update content")))
      .andExpect(jsonPath("$.title", is("title")));
  }

  @Test
  public void e_deletePost() throws Exception {
    Posts postsDomain = getPostsDomain();
    createUser(postsDomain);

    mockMvc.perform(delete("/posts/{id}", 5))
      .andDo(print())
      .andExpect(status().isNoContent());
  }

  private Posts getPostsDomain() {
    Posts posts = new Posts();
    posts.setContent("post content");
    posts.setTitle("title");
    posts.setPostStatus(PostStatus.PUBLISH);
    posts.setPostType(PostType.POST);
    posts.setCommentStatus(CommentStatus.OPEN);
    return posts;
  }

  private void createUser(Posts posts) throws Exception {
    mockMvc.perform(post("/posts").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(posts)))
      .andDo(print())
      .andExpect(status().isCreated());
  }

//  private Users getUsersDomain() {
//    Users users = new Users();
//    users.setLogin("wonwoo");
//    users.setPass("123");
//    users.setNickName("lww");
//    users.setEmail("wonwoo@test.com");
//    users.setDisplayName("wonwoo");
//    return users;
//  }
//
//  private void createUser(Users users) throws Exception {
//    mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(users)))
//      .andDo(print())
//      .andExpect(status().isCreated());
//  }
}