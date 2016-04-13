package me.wonwoo.config.annotation;

import org.springframework.http.response.success.ResponseStatusOk;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@ResponseStatusOk
public @interface RestControllerOk {
}
