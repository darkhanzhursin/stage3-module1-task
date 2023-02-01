package com.mjc.school;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Menu menu = new Menu();
    Controller controller = new Controller();
    while (true) {
      try {
        menu.printMenu();
        String key = input.nextLine();
        switch (key) {
          case "1" -> menu.getNews(controller);
          case "2" -> menu.getNewsById(controller, input);
          case "3" -> menu.createNews(controller, input);
          case "4" -> menu.updateNews(controller, input);
          case "5" -> menu.deleteNews(controller, input);
          case "6" -> System.exit(0);
          default -> System.out.println(Menu.COMMAND_NOT_FOUND);
        }
      } catch (RuntimeException ex) {
        System.out.println(ex.getMessage());
      }
    }
  }
}