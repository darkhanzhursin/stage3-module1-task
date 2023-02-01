package com.mjc.school.model.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mjc.school.model.AuthorModel;
import com.mjc.school.model.NewsModel;
import com.mjc.school.utils.Utils;

public class NewsData {
  private final static String CONTENT_FILE_NAME = "content";
  private final static String NEWS_FILE_NAME = "news";
  private static NewsData newsData;
  private List<NewsModel> newsModelList;

  private NewsData(List<AuthorModel> authorModelList) {
    init(authorModelList);
  }

  private void init(final List<AuthorModel> authorModelList) {
    newsModelList = new ArrayList<>();
    Random random = new Random();
    for (long i = 0; i < 20; i++) {
      LocalDateTime date = Utils.getRandomDate();
      newsModelList.add(new NewsModel(i, Utils.getRandomContentByFilePath(NEWS_FILE_NAME),
          Utils.getRandomContentByFilePath(CONTENT_FILE_NAME), date, date,
          authorModelList.get(random.nextInt(authorModelList.size())).getId())
      );
    }
  }

  public static NewsData getNewsData(List<AuthorModel> authorModelList) {
    if (newsData == null) {
      newsData = new NewsData(authorModelList);
    }
    return newsData;
  }

  public List<NewsModel> getNewsList() {
    return newsModelList;
  }
}
