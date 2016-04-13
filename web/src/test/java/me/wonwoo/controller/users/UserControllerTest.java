package me.wonwoo.controller.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.Application;
import me.wonwoo.domain.users.Users;
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
public class UserControllerTest {

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
  public void a_createUser() throws Exception {
    Users users = getUsersDomain();
    createUser(users);
  }

  @Test
  public void b_createUserBadRequest() throws Exception {

    Users users = new Users();
    users.setLogin("won");
    users.setPass("123");
    mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(users)))
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

  @Test
  public void c_patchUser() throws Exception {
    Users users = getUsersDomain();
    createUser(users);

    Users patchUser = new Users();
    patchUser.setLogin("wonwoo");
    patchUser.setNickName("patchwonwoo");
    mockMvc.perform(patch("/users/{id}", 2).content(objectMapper.writeValueAsString(patchUser)).contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.login", is("wonwoo")))
      .andExpect(jsonPath("$.pass", is("123")))
      .andExpect(jsonPath("$.nickName", is("patchwonwoo")))
      .andExpect(jsonPath("$.email", is("wonwoo@test.com")))
      .andExpect(jsonPath("$.displayName", is("wonwoo")));

  }

  @Test
  public void ca_patchUserIdNotFoundBadRequestException() throws Exception {
    Users patchUser = new Users();
    patchUser.setNickName("patchwonwoo");
    mockMvc.perform(patch("/users/{id}", 1).content(objectMapper.writeValueAsString(patchUser)).contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

  @Test
  public void cb_patchUserBadRequestException() throws Exception {
    Users users = getUsersDomain();
    createUser(users);
    Users patchUser = new Users();
    patchUser.setLogin("won");
    mockMvc.perform(patch("/users/{id}", 3).content(objectMapper.writeValueAsString(patchUser)).contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isBadRequest());
  }

  @Test
  public void d_getUsers() throws Exception {
    Users users = getUsersDomain();
    createUser(users);

    mockMvc.perform(get("/users"))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.content.[0].login", is("wonwoo")))
      .andExpect(jsonPath("$.content.[0].pass", is("123")))
      .andExpect(jsonPath("$.content.[0].nickName", is("lww")))
      .andExpect(jsonPath("$.content.[0].email", is("wonwoo@test.com")))
      .andExpect(jsonPath("$.content.[0].displayName", is("wonwoo")));
  }

  @Test
  public void e_getUser() throws Exception {
    Users users = getUsersDomain();
    createUser(users);

    mockMvc.perform(get("/users/{id}", 5))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.login", is("wonwoo")))
      .andExpect(jsonPath("$.pass", is("123")))
      .andExpect(jsonPath("$.nickName", is("lww")))
      .andExpect(jsonPath("$.email", is("wonwoo@test.com")))
      .andExpect(jsonPath("$.displayName", is("wonwoo")));
  }


  @Test
  public void f_deleteUser() throws Exception {
    Users users = getUsersDomain();
    createUser(users);

    mockMvc.perform(delete("/users/{id}", 6))
      .andDo(print())
      .andExpect(status().isNoContent());
  }


  private Users getUsersDomain() {
    Users users = new Users();
    users.setLogin("wonwoo");
    users.setPass("123");
    users.setNickName("lww");
    users.setEmail("wonwoo@test.com");
    users.setDisplayName("wonwoo");
    return users;
  }

  private void createUser(Users users) throws Exception {
    mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(users)))
      .andDo(print())
      .andExpect(status().isCreated());
  }
}