package com.mjc.school.model;

import java.time.LocalDateTime;

public class NewsModel {

  private Long id;
  private String title;
  private String content;
  private LocalDateTime createDate;
  private LocalDateTime lastUpdatedDate;
  private Long authorId;

  public NewsModel(Long id, String title, String content, LocalDateTime createDate, LocalDateTime lastUpdatedDate, Long authorId) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.createDate = createDate;
    this.lastUpdatedDate = lastUpdatedDate;
    this.authorId = authorId;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(final String content) {
    this.content = content;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(final LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public LocalDateTime getLastUpdatedDate() {
    return lastUpdatedDate;
  }

  public void setLastUpdatedDate(final LocalDateTime lastUpdatedDate) {
    this.lastUpdatedDate = lastUpdatedDate;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(final Long authorId) {
    this.authorId = authorId;
  }

  @Override
  public String toString() {
    return "NewsDtoResponse[" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", createDate=" + createDate +
        ", lastUpdatedDate=" + lastUpdatedDate +
        ", authorId=" + authorId
        + ']';
  }
}
