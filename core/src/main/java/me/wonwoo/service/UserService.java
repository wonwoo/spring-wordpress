package me.wonwoo.service;

import me.wonwoo.domain.users.Users;
import me.wonwoo.exception.IdNotFoundException;
import me.wonwoo.repository.UserRepository;
import me.wonwoo.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Transactional(readOnly = true)
  public Page<Users> findUsers(Pageable pageable){
    return userRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Users findUser(Long id){
    return getUser(id);
  }

  private Users getUser(Long id) {
    Users users = userRepository.findOne(id);
    if(users == null){
      throw new IdNotFoundException(id);
    }
    return users;
  }

  public Users save(Users user){
    return userRepository.save(user);
  }

  public Users update(Long id, Users user){
    Users oldUsers = findUser(id);
    if (oldUsers == null) {
      throw new IdNotFoundException(id);
    }
    ReflectionUtils.oldInstanceBynewInstance(oldUsers, user);
    return oldUsers;

  }

  public void delete(Long id){
    userRepository.delete(id);
  }
}
