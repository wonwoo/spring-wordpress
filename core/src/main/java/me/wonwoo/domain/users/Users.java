package me.wonwoo.domain.users;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.swing.text.StyleConstants.Size;

/**
 * Created by wonwoo on 2016. 4. 12..
 */
@Entity
@Data
@ToString(exclude = {"userMeta", "comments"})
public class Users {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "USER_LOGIN")
  private String login;

  @Column(name = "USER_PASS")
  private String pass;

  @Column(name = "USER_NICKNAME")
  private String nickName;

  @Column(name = "USER_EMAIL")
  private String email;

  @Column(name = "USER_URL")
  private String url;

  @Column(name = "USER_REGISTERED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date registered;

  @Column(name = "USER_ACTIVATION_KEY")
  private String activationKey;

  @Column(name = "USER_STATUS")
  private Integer status;

  @Column(name = "DISPLAY_NAME")
  private String displayName;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
  private List<UserMeta> userMeta = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
  private List<Comments> comments = new ArrayList<>();

}
