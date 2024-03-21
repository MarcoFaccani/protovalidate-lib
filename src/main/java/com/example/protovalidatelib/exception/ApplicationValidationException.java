package com.example.protovalidatelib.exception;

import java.util.Map;

import lombok.Getter;

@Getter
public class ApplicationValidationException extends RuntimeException {

  private final Map<String, String> validationIssues;

  public ApplicationValidationException(String exception, Map<String, String> validationIssues) {
    super(exception);
    this.validationIssues = validationIssues;
  }

}
