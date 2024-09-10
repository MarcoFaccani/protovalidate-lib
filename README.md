
## Overview
This is a Java library to validate gRPC messages using protovalidate library.

## Protovalidate resources
* [Protovalidate GitHub page](https://github.com/bufbuild/protovalidate)
* [Standard Constraints](https://github.com/bufbuild/protovalidate/blob/main/docs/standard-constraints.md)
* [Java Protovalide Dependency](https://github.com/bufbuild/protovalidate-java)


## How To Use this library
Import in your project this dependency:
```
<dependency>
    <groupId>com.example</groupId>
    <artifactId>protovalidate-lib</artifactId>
    <version>0.0.6-SNAPSHOT</version>
</dependency>
```

Add to a config file the following import:
```
@Import({GrpcMessageValidator.class, Validator.class})
```
In your gRPC Server class, add the following field member:
```
private final GrpcMessageValidator grpcRequestValidator;
```

Finally, in your gRPC API entrypoint method, add the following:

```
grpcRequestValidator.validate(request);
```
It will trigger the validation declared in the proto file using the protovalidate lib syntax (see mentioned resources) and throw an `ApplicationValidationException` with all validation constraints broken a single message. This is a sample message received by a client:
```
{
  "error": "3 INVALID_ARGUMENT: surname: value length must be at least 1 characters; name: value is required"
}
```
