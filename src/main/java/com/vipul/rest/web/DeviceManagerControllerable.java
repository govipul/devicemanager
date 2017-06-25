package com.vipul.rest.web;

import java.util.List;

import com.vipul.rest.model.Device;

public interface DeviceManagerControllerable {
  public Device registerDevice(final String name, final String key);

  public List<Device> getAllDevice();

  public Device getDevice(final String name) throws Exception;

  public Device updateStatus(final String key, final String status) throws Exception;

  Device getDeviceByStatus(String status) throws Exception;
}
