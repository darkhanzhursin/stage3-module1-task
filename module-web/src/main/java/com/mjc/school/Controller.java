package com.mjc.school;

import java.util.List;

import com.mjc.school.dto.NewsDtoRequest;
import com.mjc.school.dto.NewsDtoResponse;
import com.mjc.school.factory.Factory;
import com.mjc.school.interfaces.Service;

public class Controller {
  private final Service<NewsDtoRequest, NewsDtoResponse> newsService;

  public Controller() {
    newsService = Factory.getInstance().getNewsService();
  }

  public List<NewsDtoResponse> readAll() {
    return newsService.readAll();
  }

  public NewsDtoResponse readById(Long id) {
    return newsService.readById(id);
  }

  public NewsDtoResponse create(NewsDtoRequest request) {
    return newsService.create(request);
  }

  public NewsDtoResponse update(NewsDtoRequest request) {
    return newsService.update(request);
  }

  public Boolean deleteById(Long id) {
    return newsService.deleteById(id);
  }
}
