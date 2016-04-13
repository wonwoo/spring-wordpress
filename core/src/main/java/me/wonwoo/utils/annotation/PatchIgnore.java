package me.wonwoo.utils.annotation;

import java.lang.annotation.*;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PatchIgnore {
}