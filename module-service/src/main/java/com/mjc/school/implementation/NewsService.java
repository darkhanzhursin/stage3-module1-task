package com.mjc.school.implementation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.mjc.school.dto.NewsDtoRequest;
import com.mjc.school.dto.NewsDtoResponse;
import com.mjc.school.exceptions.NotFoundException;
import com.mjc.school.exceptions.ServiceErrorException;
import com.mjc.school.factory.RepositoryFactory;
import com.mjc.school.interfaces.ModelMapper;
import com.mjc.school.interfaces.Repository;
import com.mjc.school.interfaces.Service;
import com.mjc.school.model.NewsModel;
import com.mjc.school.validation.Validation;

import org.mapstruct.factory.Mappers;

public class NewsService implements Service<NewsDtoRequest, NewsDtoResponse> {
  private final Repository<NewsModel> newsRepository;
  private final Validation validation;
  private ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

  public NewsService() {
    this.newsRepository = RepositoryFactory.getInstance().getNewsRepository();
    validation = Validation.getNewsValidator();
  }
  @Override public List<NewsDtoResponse> readAll() {
    return mapper.modelListToDtoList(newsRepository.readAll());
  }

  @Override public NewsDtoResponse readById(Long newsId) {
    validation.validateNewsId(newsId);
    if (newsRepository.isExistById(newsId)) {
      NewsModel newsModel = newsRepository.readById(newsId);
      return mapper.modelToDto(newsModel);
    } else {
      throw new NotFoundException(String.format(String.valueOf(ServiceErrorException.NEWS_ID_DOES_NOT_EXIST.getMessage()), newsId));
    }
  }

  @Override public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
    validation.validateNewsDto(dtoRequest);
    NewsModel model = mapper.dtoToModel(dtoRequest);
    LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    model.setCreateDate(date);
    model.setLastUpdatedDate(date);
    NewsModel newsModel = newsRepository.create(model);
    return mapper.modelToDto(newsModel);
  }

  @Override public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
    validation.validateNewsId(dtoRequest.id());
    validation.validateNewsDto(dtoRequest);
    if (newsRepository.isExistById(dtoRequest.id())) {
      NewsModel model = mapper.dtoToModel(dtoRequest);
      LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
      model.setLastUpdatedDate(date);
      NewsModel newsModel = newsRepository.update(model);
      return mapper.modelToDto(newsModel);
    } else {
      throw new NotFoundException(String.format(ServiceErrorException.NEWS_ID_DOES_NOT_EXIST.getMessage(), dtoRequest.id()));
    }
  }

  @Override public Boolean deleteById(Long newsId) {
    validation.validateNewsId(newsId);
    if (newsRepository.isExistById(newsId)) {
      return newsRepository.removeById(newsId);
    } else {
      throw new NotFoundException(String.format(ServiceErrorException.NEWS_ID_DOES_NOT_EXIST.getMessage(), newsId));
    }
  }
}
