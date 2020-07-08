package com.tszyi.springbootdemo.exception;

public class SqlOperationException extends RuntimeException {
  public SqlOperationException() {
    super();
  }

  public SqlOperationException(String msg) {
    super(msg);
  }

  public SqlOperationException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public SqlOperationException(Throwable cause) {
    super(cause);
  }
}
