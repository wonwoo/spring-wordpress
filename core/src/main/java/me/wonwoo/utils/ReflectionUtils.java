package me.wonwoo.utils;

import me.wonwoo.exception.ReflecationException;
import me.wonwoo.utils.annotation.PatchIgnore;

import javax.persistence.Id;
import java.lang.reflect.Field;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
public class ReflectionUtils {

  public static <T> void oldInstanceBynewInstance(T oldInstance, T newInstance) {
    Class<?> newInstanceClass = newInstance.getClass();
    Class<?> oldInstanceClass = oldInstance.getClass();

    if(!newInstanceClass.isAssignableFrom(oldInstanceClass)){
      return;
    }

    for (Field newField : newInstanceClass.getDeclaredFields()) {
      newField.setAccessible(true);
      Object obj;
      try {
        obj = newField.get(newInstance);
      } catch (IllegalAccessException e) {
        throw new ReflecationException("reflecation Exception get field");
      }
      Id id = newField.getAnnotation(Id.class);
      PatchIgnore patchIgnore = newField.getAnnotation(PatchIgnore.class);
      if (id == null) {
        if (obj != null && patchIgnore == null) {
          Field oldField;
          try {
            oldField = oldInstanceClass.getDeclaredField(newField.getName());
            oldField.setAccessible(true);
            oldField.set(oldInstance, obj);
          } catch (NoSuchFieldException e) {
            throw new ReflecationException("no such field");
          } catch (IllegalAccessException e) {
            throw new ReflecationException("reflecation Exception set field");
          }
        }
      }
    }
  }

}
