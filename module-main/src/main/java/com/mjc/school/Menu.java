package com.mjc.school;

import java.util.Scanner;

import com.mjc.school.dto.NewsDtoRequest;
import com.mjc.school.exceptions.ServiceErrorException;
import com.mjc.school.exceptions.ValidatorException;

public class Menu {
  public static final String NEWS_ID = "News Id";
  public static final String AUTHOR_ID = "Author Id";
  public static final String NUMBER_OPERATION_ENTER = "Enter the number of operation: ";
  public static final String NEWS_ID_ENTER = "Enter news id: ";
  public static final String NEWS_TITLE_ENTER = "Enter news title: ";
  public static final String NEWS_CONTENT_ENTER = "Enter news content: ";
  public static final String AUTHOR_ID_ENTER = "Enter author id: ";
  public static final String COMMAND_NOT_FOUND = "Command not found.";
  public static final String OPERATION = "OPERATION: ";

  public void printMenu() {
    System.out.println(NUMBER_OPERATION_ENTER);
    for (Operation operation : Operation.values()) {
      System.out.println(operation.getActionNumber());
    }
  }

  public void getNews(Controller controller) {
    System.out.println(Operation.GET_ALL_NEWS.getAction());
    controller.readAll().forEach(System.out::println);
  }

  public void getNewsById(Controller controller, Scanner scanner) {
    System.out.println(Operation.GET_NEWS_BY_ID.getAction());
    System.out.println(NEWS_ID_ENTER);
    System.out.println(controller.readById(getEnteredNumber(NEWS_ID, scanner)));
  }

  public void createNews(Controller controller, Scanner scanner) {
    NewsDtoRequest request = null;
    boolean isValid = false;
    while (!isValid) {
      try {
        System.out.println(Operation.CREATE_NEWS.getAction());
        System.out.println(NEWS_TITLE_ENTER);
        String title = scanner.nextLine();
        System.out.println(NEWS_CONTENT_ENTER);
        String content = scanner.nextLine();
        System.out.println(AUTHOR_ID_ENTER);
        Long authorId = getEnteredNumber(AUTHOR_ID, scanner);
        request = new NewsDtoRequest(null, title, content, authorId);
        isValid = true;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    System.out.println(controller.create(request));
  }

  public void updateNews(Controller controller, Scanner scanner) {
    NewsDtoRequest dtoRequest = null;
    boolean isValid = false;
    while (!isValid) {
      try {
        System.out.println(Operation.UPDATE_NEWS.getAction());
        System.out.println(NEWS_ID_ENTER);
        Long newsId = getEnteredNumber(NEWS_ID, scanner);
        System.out.println(NEWS_TITLE_ENTER);
        String title = scanner.nextLine();
        System.out.println(NEWS_CONTENT_ENTER);
        String content = scanner.nextLine();
        System.out.println(AUTHOR_ID_ENTER);
        Long authorId = getEnteredNumber(AUTHOR_ID, scanner);
        dtoRequest = new NewsDtoRequest(newsId, title, content, authorId);
        isValid = true;
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    System.out.println(controller.update(dtoRequest));
  }

  public void deleteNews(Controller controller, Scanner scanner) {
    System.out.println(Operation.REMOVE_NEWS_BY_ID.getAction());
    System.out.println(NEWS_ID_ENTER);
    System.out.println(controller.deleteById(getEnteredNumber(NEWS_ID, scanner)));
  }
  private long getEnteredNumber(String params, Scanner scanner) {
    long number;
    try {
      number = scanner.nextLong();
      scanner.nextLine();
    } catch (Exception ex) {
      scanner.nextLine();
      throw new ValidatorException(
          String.format(ServiceErrorException.VALIDATE_INT_VALUE.getMessage(), params)
      );
    }
    return number;
  }
}
