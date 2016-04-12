package me.wonwoo.domain.posts;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by wonwoo on 2016. 4. 13..
 */
@Entity
@Data
public class Posts {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "POST_AUTHOR")
  private Long author;

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

  @Column(name = "POST_STATUS")
  private String status;

  @Column(name = "COMMENT_STATUS")
  private String commentStatus;

  @Column(name = "PING_STATUS")
  private String pingStatus;

  @Column(name = "POST_PASSWORD")
  private String password;

  @Column(name = "POST_NAME")
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
  private String guid;

  @Column(name = "MENU_ORDER")
  private Integer menuOrder;

  @Column(name = "POST_TYPE")
  private String postType;

  @Column(name = "POST_MIME_TYPE")
  private String mimeType;

  @Column(name = "COMMENT_COUNT")
  private Long commentCount;


}
