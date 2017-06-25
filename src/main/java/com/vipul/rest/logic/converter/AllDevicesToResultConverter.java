package com.vipul.rest.logic.converter;

import java.util.ArrayList;
import java.util.List;

import com.vipul.rest.model.Device;

public class AllDevicesToResultConverter {
  public List<Device> convert(final List<Device> devices) {
    List<Device> returnList = new ArrayList<Device>();
    for (Device device : devices) {
      returnList.add(new Device(device.getName(), "", device.getStatus()));
    }
    return returnList;
  }
}
