package com.mjc.school.model.data;

import java.util.ArrayList;
import java.util.List;

import com.mjc.school.model.AuthorModel;
import com.mjc.school.utils.Utils;

public class AuthorData {

  private List<AuthorModel> authorList;
  private static AuthorData authorData;
  private final static String AUTHORS_FILE_NAME = "authors";

  private AuthorData() {
    init();
  }

  public static AuthorData getAuthorData() {
    if (authorData == null) {
      authorData = new AuthorData();
    }
    return authorData;
  }

  private void init() {
    authorList = new ArrayList<>();
    for (long i = 1; i <= 20; i++) {
      authorList.add(new AuthorModel(i, Utils.getRandomContentByFilePath(AUTHORS_FILE_NAME)));
    }
  }

  public List<AuthorModel> getAuthorList() {
    return authorList;
  }
}
