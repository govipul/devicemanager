package com.iamonlygo.rest.exception;

public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = -5623955663923290385L;

  public static final String DEVICE_NAME_MISSING =
      "You need to provide a device name using 'name' parameter!";

  public static final String DEVICE_KEY_MISSING =
      "You need to provide a device key using 'key' parameter!";

  public BadRequestException() {
    super();
  }

  public BadRequestException(final String message) {
    super(message);
  }

  public BadRequestException(final Throwable cause) {
    super(cause);
  }

  public BadRequestException(final String message, final Throwable cause) {
    super(message, cause);
  }

}
