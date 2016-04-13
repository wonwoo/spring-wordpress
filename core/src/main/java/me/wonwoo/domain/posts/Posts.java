package me.wonwoo.domain.posts;

import lombok.Data;
import me.wonwoo.domain.posts.enumerated.CommentStatus;
import me.wonwoo.domain.posts.enumerated.PostStatus;
import me.wonwoo.domain.posts.enumerated.PostType;
import me.wonwoo.domain.users.Users;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@Entity
@Data
public class Posts {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "POST_AUTHOR")
  private Users author;

  @Column(name = "POST_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Column(name = "POST_DATE_GMT")
  private Date dateGmt;

  @Column(name = "POST_CONTENT")
  @Lob
  private String content;

  @Column(name = "POST_TITLE", columnDefinition = "text")
  private String title;

  @Column(name = "POST_EXCERPT", columnDefinition = "text")
  private String excerpt;

  @Column(name = "POST_STATUS", length = 20)
  @Enumerated(EnumType.STRING)
  private PostStatus postStatus;

  @Column(name = "COMMENT_STATUS", length = 20)
  @Enumerated(EnumType.STRING)
  private CommentStatus commentStatus;

  @Column(name = "PING_STATUS")
  @Size(max = 20)
  private String pingStatus;

  @Column(name = "POST_PASSWORD")
  @Size(max = 20)
  private String password;

  @Column(name = "POST_NAME")
  @Size(max = 200)
  private String name;

  @Column(name = "TO_PING", columnDefinition = "text")
  private String toPing;

  @Column(name = "PINGED", columnDefinition = "text")
  private String pinged;

  @Column(name = "POST_MODIFIED")
  @Temporal(TemporalType.TIMESTAMP)
  private Date modified;

  @Column(name = "POST_MODIFIED_GMT")
  private Date modifiedGmt;

  @Column(name = "POST_CONTENT_FILTERED")
  @Lob
  private String contentFiltered;

  @Column(name = "POST_PARENT")
  private Long parent;

  @Column(name = "GUID")
  @Size(max = 255)
  private String guid;

  @Column(name = "MENU_ORDER")
  private Integer menuOrder;

  @Column(name = "POST_TYPE", length = 20)
  @Enumerated(EnumType.STRING)
  private PostType postType;

  @Column(name = "POST_MIME_TYPE")
  @Size(max = 100)
  private String mimeType;

  @Column(name = "COMMENT_COUNT")
  private Long commentCount;
}
