package com.mjc.school.factory;

import com.mjc.school.repository.NewsService;

public class Factory {
  private static Factory instance;
  private final NewsService newsService;

  private Factory() {
    newsService = new NewsService();
  }

  public static Factory getInstance() {
    if (instance == null) {
      instance = new Factory();
    }
    return instance;
  }

  public NewsService getNewsService() {
    return newsService;
  }
}
