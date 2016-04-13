package me.wonwoo.controller.users;

import lombok.extern.slf4j.Slf4j;
import me.wonwoo.config.annotation.RestControllerOk;
import me.wonwoo.domain.users.Users;
import me.wonwoo.exception.BadRequestException;
import me.wonwoo.repository.users.UserRepository;
import me.wonwoo.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.response.success.ResponseStatusCreated;
import org.springframework.http.response.success.ResponseStatusNoContent;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by wonwoo on 2016. 4. 12..
 */
@RestControllerOk
@Slf4j
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public Page<Users> findUsers(Pageable pageable) {
    Page<Users> users = userRepository.findAll(pageable);
    users.getContent().stream().map(x -> x.toString()).forEach(log::info);
    return users;
  }

  @GetMapping("/users/{id}")
  public Users findUser(@PathVariable Long id) {
    return userService.findUser(id);
  }

  @PostMapping("/users")
  @ResponseStatusCreated
  public Users save(@Valid @RequestBody Users users, BindingResult bs) {
    if (bs.hasErrors()) {
      throw new BadRequestException(bs.getFieldError().getDefaultMessage());
    }
    return userService.save(users);
  }

  @PatchMapping("/users/{id}")
  public Users update(@PathVariable Long id, @Valid @RequestBody Users users, BindingResult bs) {
    if (bs.hasErrors()) {
      throw new BadRequestException(bs.getFieldError().getDefaultMessage());
    }
    return userService.update(id, users);
  }

  @DeleteMapping("/users/{id}")
  @ResponseStatusNoContent
  public void delete(@PathVariable Long id) {
    userService.delete(id);
  }

  //TODO 일단 이것만
  //화면 어떤거 react? thymeleaf? 결정
  //클라이언트 셋팅
  //entity 다시 확인
}
