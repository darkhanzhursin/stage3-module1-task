package com.mjc.school;

import static com.mjc.school.Menu.OPERATION;

public enum Operation {
  GET_ALL_NEWS(1, "Get all news."),
  GET_NEWS_BY_ID(2, "Get news by id."),
  CREATE_NEWS(3, "Create news."),
  UPDATE_NEWS(4, "Update news."),
  REMOVE_NEWS_BY_ID(5, "Remove news by id."),
  EXIT(6, "Exit.");

  private final Integer operationNumber;
  private final String operation;

  Operation(Integer operationNumber, String operation) {
    this.operationNumber = operationNumber;
    this.operation = operation;
  }

  public String getAction() {
    return OPERATION + operation;
  }

  public String getActionNumber() {
    return operationNumber + ": " + operation;
  }
}
