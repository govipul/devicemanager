package com.iamonlygo.rest.exception;

public class DeviceManagerException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DeviceManagerException() {
    super();
  }

  public DeviceManagerException(final String message) {
    super(message);
  }

  public DeviceManagerException(final Throwable cause) {
    super(cause);
  }

  public DeviceManagerException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
