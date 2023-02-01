package com.mjc.school.factory;

import com.mjc.school.implementation.NewsRepository;
import com.mjc.school.interfaces.Repository;
import com.mjc.school.model.NewsModel;

public class RepositoryFactory {
  private static RepositoryFactory instance;
  private final Repository<NewsModel> newsRepository;

  private RepositoryFactory() {
    newsRepository = new NewsRepository();
  }

  public static RepositoryFactory getInstance() {
    if (instance == null) {
      instance = new RepositoryFactory();
    }
    return instance;
  }

  public Repository<NewsModel> getNewsRepository() {
    return newsRepository;
  }
}
