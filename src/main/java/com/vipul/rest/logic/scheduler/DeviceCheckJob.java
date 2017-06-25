package com.vipul.rest.logic.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vipul.rest.model.Device;
import com.vipul.rest.model.DeviceManagerStatus;

public class DeviceCheckJob implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(DeviceCheckJob.class);

  private static List<Device> devices;

  private static DeviceCheckJob job;

  public static DeviceCheckJob getInstance() {
    if (devices == null) {
      devices = new ArrayList<>();
    }
    if (job == null) {
      job = new DeviceCheckJob();
    }
    return job;
  }

  public void addDevice(Device device) {
    if (!devices.contains(device)) {
      devices.add(device);
    }
  }


  @Override
  public void run() {
    logger.info("Scanning queue contains " + devices.size() + " devices...");
    for (Device device : devices) {
      long currentTime = System.currentTimeMillis();
      if (device.getStatus() == DeviceManagerStatus.OK
          && (currentTime - device.getModified()) >= 3000) {
        device.setStatus(DeviceManagerStatus.STALE);
        logger.info("Changing the device status to Stale:" + device);
      }
    }
  }

  public void removeDevice(Device device) {
    if (devices.contains(device)) {
      devices.remove(device);
    }
  }

}
