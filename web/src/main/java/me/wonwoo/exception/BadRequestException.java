package me.wonwoo.exception;

import org.springframework.http.response.client.ResponseStatusBadRequest;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@ResponseStatusBadRequest
public class BadRequestException extends RuntimeException{
  private String message;
  public BadRequestException(String message){
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
