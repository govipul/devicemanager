package com.vipul.rest.persistance.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vipul.rest.model.Device;
import com.vipul.rest.persistance.api.DeviceManagerPersistanceable;

public class DeviceManagerPersistance implements DeviceManagerPersistanceable {

  private static final List<Device> data = new ArrayList<>();

  public Device addDevice(String name, String key) throws Exception {
    Device device = new Device(name, key);
    if (data.contains(device)) {
      throw new Exception("Object already exists in the system");
    }
    data.add(device);
    return device
  }

  public Device getDeviceByName(String name) throws Exception {
    if (StringUtils.isEmpty(name)) {
      throw new Exception("Please provide valid NAME");
    }

    Device returnDevice = null;
    for (Device device : data) {
      if (device.getName().equalsIgnoreCase(name)) {
        returnDevice = device;
        break;
      }
    }
    return returnDevice;
  }

  public Device getDeviceByKey(String key) throws Exception {
    if (StringUtils.isEmpty(key)) {
      throw new Exception("Please provide valid KEY");
    }

    Device returnDevice = null;
    for (Device device : data) {
      if (device.getKey().equalsIgnoreCase(key)) {
        returnDevice = device;
        break;
      }
    }
    return returnDevice;
  }

  public List<Device> getAllDevice() throws Exception {
    if (data.isEmpty()) {
      throw new Exception("Please enter add device to the data");
    }
    return data;
  }

  public boolean removeDevice(Device device) {
    if (device == null) {
      throw new NullPointerException("Device is null");
    }
    return data.remove(device);
  }

}
