package com.mjc.school.exceptions;

public enum ServiceErrorException {
  NEWS_ID_DOES_NOT_EXIST(Constants.ERROR_000001, "News with id %d does not exist."),
  AUTHOR_ID_DOES_NOT_EXIST(Constants.ERROR_000002, "Author Id does not exist. Author Id is: %s"),
  VALIDATE_NEGATIVE_OR_NULL_NUMBER(Constants.ERROR_000010, "%s can not be null or less than 1. %s is: %s"),
  VALIDATE_NULL_STRING(Constants.ERROR_000011, "%s can not be null. %s is null"),
  VALIDATE_STRING_LENGTH(Constants.ERROR_000012, "%s can not be less than %d and more than %d symbols. %s is %s"),
  VALIDATE_INT_VALUE(Constants.ERROR_000013, "%s should be number");

  private final String errorMessage;

  ServiceErrorException(String errorCode, String errorMessage) {
    this.errorMessage = "ERROR_CODE: " + errorCode + " ERROR_MESSAGE: " + errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public String getMessage() {
    return errorMessage;
  }

  private static class Constants {
    private static final String ERROR_000001 = "000001";
    private static final String ERROR_000002 = "000002";
    private static final String ERROR_000010 = "000010";
    private static final String ERROR_000011 = "000011";
    private static final String ERROR_000012 = "000012";
    private static final String ERROR_000013 = "000013";

    private Constants() {}
  }
}

