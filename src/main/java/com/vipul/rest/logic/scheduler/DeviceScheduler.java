package com.vipul.rest.logic.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.vipul.rest.model.Device;

public class DeviceScheduler {

  private static DeviceScheduler instance;
  private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(5);
  private final DeviceCheckJob checkJob;

  private DeviceScheduler() {
    checkJob = DeviceCheckJob.getInstance();
    SCHEDULER.scheduleWithFixedDelay(checkJob, 5, 5, TimeUnit.MINUTES);
  }

  public static DeviceScheduler getInstance() {
    if (instance == null) {
      instance = new DeviceScheduler();
    }
    return instance;
  }

  public void add(Device device) {
    checkJob.addDevice(device);
  }

  public void remove(Device device) {
    checkJob.removeDevice(device);
  }
}
