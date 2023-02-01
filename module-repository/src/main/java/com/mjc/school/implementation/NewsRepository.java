package com.mjc.school.implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.mjc.school.interfaces.Repository;
import com.mjc.school.model.NewsModel;
import com.mjc.school.utils.DataSource;

public class NewsRepository implements Repository<NewsModel> {

  private final DataSource dataSource;

  public NewsRepository() {
    this.dataSource = DataSource.getInstance();
  }

  @Override
  public List<NewsModel> readAll() {
    return dataSource.getNews();
  }

  @Override
  public NewsModel create(NewsModel model) {
    List<NewsModel> newsModels = dataSource.getNews();
    newsModels.sort(Comparator.comparing(NewsModel::getId));
    if (!newsModels.isEmpty()) {
      model.setId(newsModels.get(newsModels.size() - 1).getId() + 1);
    } else {
      model.setId(1L);
    }
    newsModels.add(model);
    return model;
  }

  @Override
  public NewsModel update(NewsModel model) {
    NewsModel newsModel = readById(model.getId());
    newsModel.setTitle(model.getTitle());
    newsModel.setContent(model.getContent());
    newsModel.setAuthorId(model.getAuthorId());
    newsModel.setLastUpdatedDate(model.getLastUpdatedDate());
    return newsModel;
  }

  @Override
  public NewsModel readById(Long newsId) {
    return dataSource.getNews().stream().filter(news -> newsId.equals(news.getId())).findFirst().get();
  }

  @Override
  public Boolean removeById(Long newsId) {
    List<NewsModel> list = new ArrayList<>();
    list.add(this.readById(newsId));
    return dataSource.getNews().removeAll(list);
  }

  @Override
  public Boolean isExistById(Long newsId) {
    return dataSource.getNews().stream().anyMatch(news -> newsId.equals(news.getId()));
  }
}
