package com.mjc.school.validation;

import com.mjc.school.dto.NewsDtoRequest;
import com.mjc.school.exceptions.ValidatorException;

import static com.mjc.school.exceptions.ServiceErrorException.*;

public class Validation {

  private static Validation newsValidator;
  private static final String NEWS_ID = "News id";
  private static final String NEWS_CONTENT = "News content";
  private static final String AUTHOR_ID = "Author id";
  private static final String NEWS_TITLE = "News title";
  private static final Integer NEWS_CONTENT_MIN_LENGTH = 5;
  private static final Integer NEWS_CONTENT_MAX_LENGTH = 255;
  private static final Integer NEWS_TITLE_MIN_LENGTH = 5;
  private static final Integer NEWS_TITLE_MAX_LENGTH = 30;
  private static final Integer MAX_AUTHOR_ID = 20;

  public static Validation getNewsValidator() {
    if (newsValidator == null) {
      newsValidator = new Validation();
    }
    return newsValidator;
  }

  public void validateNewsId(Long newsId) {
    validateNumber(newsId, NEWS_ID);
  }

  public void validateAuthorId(Long authorId) {
    validateNumber(authorId, AUTHOR_ID);
    if (authorId > MAX_AUTHOR_ID) {
      throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), authorId));
    }
  }

  public void validateNewsDto(NewsDtoRequest dtoRequest) {
    validateString(dtoRequest.title(), NEWS_TITLE, NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
    validateString(dtoRequest.content(), NEWS_CONTENT, NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
    validateAuthorId(dtoRequest.authorId());
  }

  private void validateNumber(Long id, String parameter) {
    if (id == null || id < 1) {
      throw new ValidatorException(String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), parameter, parameter, id));
    }
  }

  private void validateString(String value, String parameter, int minLength, int maxLength) {
    if (value == null) {
      throw new ValidatorException(String.format(VALIDATE_NULL_STRING.getMessage(), parameter, parameter));
    }
    if (value.trim().length() < minLength || value.trim().length() > maxLength) {
      throw new ValidatorException(String.format(VALIDATE_STRING_LENGTH.getMessage(), parameter, minLength, maxLength, parameter, value));
    }
  }
}
