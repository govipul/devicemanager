package com.vipul.rest.persistance.api;

import java.util.List;

import com.vipul.rest.model.Device;

public interface DeviceManagerPersistanceable {
  public void addDevice(String name, String key) throws Exception;

  public Device getDeviceByName(String name) throws Exception;

  public Device getDeviceByKey(String key) throws Exception;

  public List<Device> getAllDevice() throws Exception;

  public boolean removeDevice(Device device) throws Exception;
}
