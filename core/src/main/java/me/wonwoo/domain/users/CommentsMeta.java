package me.wonwoo.domain.users;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@Entity
@Data
@ToString(exclude = {"comments"})
public class CommentsMeta {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COMMENTS_ID")
  private Comments comments;

  @Column(name = "META_KEY")
  private String metaKey;

  @Column(name = "META_VALUE")
  @Lob
  private String metaValue;

}
