package me.wonwoo.repository.posts;

import me.wonwoo.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
public interface PostRepository extends JpaRepository<Posts, Long> {
}
