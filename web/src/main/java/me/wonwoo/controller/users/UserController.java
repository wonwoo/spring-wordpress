package me.wonwoo.controller.users;

import me.wonwoo.domain.users.Users;
import me.wonwoo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wonwoo on 2016. 4. 12..
 */
@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping("user")
  public List<Users> hello(){
    return userRepository.findAll();
  }
}
