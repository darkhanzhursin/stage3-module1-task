package com.mjc.school.interfaces;

import java.util.List;

import com.mjc.school.dto.NewsDtoRequest;
import com.mjc.school.dto.NewsDtoResponse;
import com.mjc.school.model.NewsModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ModelMapper {
  List<NewsDtoResponse> modelListToDtoList(List<NewsModel> newsModelList);
  NewsDtoResponse modelToDto(NewsModel newsModel);
  @Mappings({
      @Mapping(target = "createDate", ignore = true),
      @Mapping(target = "lastUpdatedDate", ignore = true)
  })
  NewsModel dtoToModel(NewsDtoRequest newsDtoRequest);
}
