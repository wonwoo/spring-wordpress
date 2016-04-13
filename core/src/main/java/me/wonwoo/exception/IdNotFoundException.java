package me.wonwoo.exception;

import org.springframework.http.response.client.ResponseStatusBadRequest;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@ResponseStatusBadRequest
public class IdNotFoundException extends RuntimeException{
  private Long id;

  public IdNotFoundException(Long id){
    this.id = id;
  }

  @Override
  public String getMessage() {
    return this.id + " not found";
  }
}
