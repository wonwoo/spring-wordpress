package me.wonwoo.domain.users;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wonwoo on 2016. 4. 12..
 */

@Entity
@Data
@ToString(exclude = {"users", "commentsMeta"})
public class Comments {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "COMMENT_POST_ID")
  private Long postId;

  @Column(name = "COMMENT_AUTHOR")
  private String author;

  @Column(name = "COMMENT_AUTHOR_EMAIL")
  @Size(max = 100)
  private String authorEmail;

  @Column(name = "COMMENT_AUTHOR_URL")
  @Size(max = 200)
  private String authorUrl;

  @Column(name = "COMMENT_AUTHOR_IP")
  @Size(max = 100)
  private String authorIp;

  @Column(name = "COMMENT_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Column(name = "COMMENT_DATE_GMT")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date_gmt;

  @Column(name = "COMMENT_CONTENT")
  @Lob
  private String content;

  @Column(name = "COMMENT_KARMA")
  private Integer karma;

  @Column(name = "COMMENT_APPROVED")
  @Size(max = 20)
  private String approved;

  @Column(name = "COMMENT_AGENT")
  @Size(max = 255)
  private String agent;

  @Column(name = "comment_type")
  @Size(max = 20)
  private String type;

  @Column(name = "COMMENT_PARENT")
  private Long parent;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID")
  private Users users;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "comments")
  private List<CommentsMeta> commentsMeta = new ArrayList<>();


}
