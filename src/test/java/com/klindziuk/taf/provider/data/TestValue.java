/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.data;

import com.klindziuk.taf.provider.exception.PreventInstantiationException;

public final class TestValue {

  // PATH
  public static final String ADD_HABIT_PATH = "/api/v1/habit/add/";
  public static final String DELETE_HABIT_PATH = "/api/v1/habit/delete/";
  public static final String GET_HABIT_PATH = "/api/v1/habit/";
  public static final String GET_HABITS_PATH = "/api/v1/habits/";
  public static final String UPDATE_HABIT_PATH = "/api/v1/habit/update/";

  // MESSAGE
  public static final String INVALID_UUID_PATTERN =
      "must match \"^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
  public static final String NOT_FOUND_MESSAGE_FORMAT = "Habit with uuid '%s' not found";
  public static final String MUST_BE_BETWEEN_AND = "Title must be between 3 and 64 characters long";
  public static final String MUST_NOT_BE_NULL = "must not be null";
  public static final String MUST_NOT_BE_EMPTY = "must not be empty";
  public static final String VALIDATION_FAILED = "Validation failed: ";
  public static final String USER_ID_MUST_NOT_BE_NULL = "[userId: must not be null];";
  public static final String UUID_MUST_NOT_BE_NULL = "[uuid: must not be null];";
  public static final String TITLE_MUST_NOT_BE_EMPTY = "[title: must not be empty];";

  // VALUE
  public static final int MIN_ALLOWED_LENGTH = 3;
  public static final int MIN_NOT_ALLOWED_LENGTH = 2;
  public static final int MAX_ALLOWED_LENGTH = 30;
  public static final int MAX_NOT_ALLOWED_LENGTH = 31;
  public static final String NULL_STRING = null;
  public static final String EMPTY_STRING = "";

  // SIZE
  public static final int LESS_THAN_MIN_SIZE = 0;
  public static final int MIN_SIZE = 1;
  public static final int DEFAULT_SIZE = 15;
  public static final int NON_DEFAULT_SIZE = 1;
  public static final int MAX_SIZE = 100;
  public static final int GREATER_THAN_MAX_SIZE = 101;
  public static final String SIZE_MUST_BE_GREATER_THAN = "size: must be greater than or equal to 1";
  public static final String SIZE_MUST_BE_LESS_THAN = "size: must be less than or equal to 100";

  private TestValue() {
    throw new PreventInstantiationException();
  }
}
