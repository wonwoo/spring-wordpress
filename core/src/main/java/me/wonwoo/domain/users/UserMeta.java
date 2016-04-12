package me.wonwoo.domain.users;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by wonwoo on 2016. 4. 12..
 */
@Entity
@Data
@ToString(exclude = "users")
public class UserMeta {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID")
  private Users users;

  @Column(name = "META_KEY")
  private String metaKey;

  //TODO 다시 확인
  @Column(name = "META_VALUE")
  @Lob
  private String metaValue;

}
