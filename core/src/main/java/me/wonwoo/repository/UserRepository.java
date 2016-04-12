package me.wonwoo.repository;

import me.wonwoo.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wonwoo on 2016. 4. 12..
 */
public interface UserRepository extends JpaRepository<Users, Long>{
}
