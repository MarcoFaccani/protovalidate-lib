package com.example.protovalidatelib;

import java.util.Map;
import java.util.Optional;

import build.buf.protovalidate.ValidationResult;
import build.buf.protovalidate.Validator;
import build.buf.validate.Violation;
import com.example.protovalidatelib.exception.ApplicationValidationException;
import com.google.protobuf.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor
@Log4j2
public class GrpcRequestValidator {

  private final Validator validator;

  /**
   * Validates a gRPC message and throws an ApplicationValidationException if validation fails.
   * Each validation error is mapped into a map.
   *
   * @param grpcRequest The gRPC message to validate.
   *
   */
  public void validate(Message grpcRequest) {
    Optional.ofNullable(grpcRequest)
        .map(ThrowingFunction.uncheck(validator::validate))
        .filter(validationResult -> !validationResult.isSuccess())
        .map(this::convertValidationResultToMap)
        .ifPresent(validationErrorMap -> {
          if (!validationErrorMap.isEmpty()) {
            throw new ApplicationValidationException("Grpc request validation failed", validationErrorMap);
          } else {
            log.debug("Grpc request validation successful");
          }
        });
  }

  private Map<String, String> convertValidationResultToMap(ValidationResult validationResult) {
    return validationResult.getViolations().stream()
        .collect(toMap(Violation::getFieldPath, Violation::getMessage));
  }

}
