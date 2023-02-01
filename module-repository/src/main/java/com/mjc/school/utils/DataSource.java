package com.mjc.school.utils;

import java.util.List;

import com.mjc.school.model.AuthorModel;
import com.mjc.school.model.NewsModel;
import com.mjc.school.model.data.AuthorData;
import com.mjc.school.model.data.NewsData;

public class DataSource {

  private final List<NewsModel> news;

  private DataSource() {
    List<AuthorModel> authorList = AuthorData.getAuthorData().getAuthorList();
    news = NewsData.getNewsData(authorList).getNewsList();
  }

  private static class LazyDataSource {
    static final DataSource INSTANCE = new DataSource();
  }

  public static DataSource getInstance() {
    return LazyDataSource.INSTANCE;
  }

  public List<NewsModel> getNews() {
    return news;
  }
}
