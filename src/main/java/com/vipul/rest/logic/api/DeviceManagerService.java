package com.vipul.rest.logic.api;

import java.util.List;

import com.vipul.rest.model.Device;
import com.vipul.rest.model.DeviceManagerStatus;


public interface DeviceManagerService {

  public Device registerDevice(String name, String key);

  public Device getDeviceDetails(String name);

  public List<Device> listDevice();

  public List<Device> listDeviceByStatus();

  public Device updateStatus(String key, DeviceManagerStatus status) throws Exception;
}
