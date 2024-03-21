package com.example.protovalidatelib;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R> extends Function<T, R> {

  R applyThrows(T elem) throws Exception;

  @Override
  default R apply(T t) {
    try {
      return applyThrows(t);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  static <T, R> Function<T, R> uncheck(ThrowingFunction<T, R> fn) {
    return fn::apply;
  }


}
