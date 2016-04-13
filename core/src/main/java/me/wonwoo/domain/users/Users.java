package me.wonwoo.domain.users;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

  @Column(name = "USER_LOGIN", unique = true, nullable = false)
  @Size(min = 5, max = 60)
  @NotBlank
  private String login;

  @Column(name = "USER_PASS", nullable = false)
  @Size(max = 255)
  private String pass;

  @Column(name = "USER_NICKNAME")
  @Size(max = 50)
  private String nickName;

  @Column(name = "USER_EMAIL")
  @Size(max = 100)
  private String email;

  @Column(name = "USER_URL")
  @Size(max = 200)
  private String url;

  @Column(name = "USER_REGISTERED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
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
