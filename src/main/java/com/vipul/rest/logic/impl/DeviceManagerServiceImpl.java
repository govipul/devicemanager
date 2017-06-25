package com.vipul.rest.logic.impl;

import java.util.ArrayList;
import java.util.List;

import com.vipul.rest.exception.DeviceManagerException;
import com.vipul.rest.logic.api.DeviceManagerService;
import com.vipul.rest.logic.scheduler.DeviceScheduler;
import com.vipul.rest.model.Device;
import com.vipul.rest.model.DeviceManagerStatus;
import com.vipul.rest.persistance.api.DeviceManagerPersistanceable;
import com.vipul.rest.persistance.impl.DeviceManagerPersistance;

public class DeviceManagerServiceImpl implements DeviceManagerService {

  private static DeviceManagerService DEVICE_MANAGER = null;
  private DeviceManagerPersistanceable deviceManagerPersistance = null;
  private final DeviceScheduler schedule = DeviceScheduler.getInstance();



  private DeviceManagerServiceImpl() {
    deviceManagerPersistance = new DeviceManagerPersistance();
  }

  public static DeviceManagerService getInstance() {
    if (DEVICE_MANAGER == null) {
      DEVICE_MANAGER = new DeviceManagerServiceImpl();
    }
    return DEVICE_MANAGER;
  }

  @Override
  public Device registerDevice(String name, String key) {
    try {
      return deviceManagerPersistance.addDevice(name, key);
    } catch (Exception e) {
      throw new DeviceManagerException(e.getMessage(), e);
    }
  }

  @Override
  public Device getDeviceDetails(String name) {
    Device returnDevice = null;
    try {
      returnDevice = deviceManagerPersistance.getDeviceByName(name);
    } catch (Exception e) {
      throw new DeviceManagerException(e.getMessage(), e);
    }
    return returnDevice;
  }

  @Override
  public List<Device> listDevice() {
    List<Device> allDevices = null;
    try {
      allDevices = deviceManagerPersistance.getAllDevice();
    } catch (Exception e) {
      throw new DeviceManagerException(e.getMessage(), e);
    }
    return allDevices;
  }

  @Override
  public List<Device> listDeviceByStatus(DeviceManagerStatus status) {
    List<Device> devicesByStatus = new ArrayList<>();
    try {
      List<Device> allDevices = deviceManagerPersistance.getAllDevice();
      for (Device device : allDevices) {
        if (device.getStatus().equals(status)) {
          devicesByStatus.add(device);
        }
      }
    } catch (Exception e) {
      throw new DeviceManagerException(e.getMessage(), e);
    }
    return devicesByStatus;
  }

  @Override
  public Device updateStatus(String key, DeviceManagerStatus status) throws Exception {
    List<Device> allDevices = deviceManagerPersistance.getAllDevice();
    for (Device device : allDevices) {
      if (device.getKey().equalsIgnoreCase(key.toUpperCase())) {
        device.setStatus(status);
        if (status == DeviceManagerStatus.OK) {
          schedule.add(device);
        } 
        return device;
      }
    }
    return null;
  }



}
