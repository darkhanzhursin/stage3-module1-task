package com.mjc.school.interfaces;

import java.util.List;

public interface Repository<T> {
  List<T> readAll();
  T create(T model);
  T update(T model);
  T readById(Long newsId);
  Boolean removeById(Long newsId);
  Boolean isExistById(Long newsId);
}

