package com.vipul.rest.web.impl;

import java.util.Collections;
import java.util.List;

import javax.activity.InvalidActivityException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vipul.rest.exception.BadRequestException;
import com.vipul.rest.logic.api.DeviceManagerService;
import com.vipul.rest.logic.converter.AllDevicesToResultConverter;
import com.vipul.rest.logic.impl.DeviceManagerServiceImpl;
import com.vipul.rest.model.Device;
import com.vipul.rest.model.DeviceManagerStatus;
import com.vipul.rest.web.DeviceManagerControllerable;

@Path("/device-manager")
public class DeviceManagerController implements DeviceManagerControllerable {

  private final DeviceManagerService service;
  private final AllDevicesToResultConverter converter = new AllDevicesToResultConverter();
  private static final Logger logger = LoggerFactory.getLogger(DeviceManagerController.class);

  public DeviceManagerController() {
    service = DeviceManagerServiceImpl.getInstance();
  }


  @POST
  @Override
  @Path("/registerDevice")
  @Produces(MediaType.APPLICATION_JSON)
  public Device registerDevice(@QueryParam("name") final String name,
      @QueryParam("key") final String key) {

    if (StringUtils.isEmpty(name)) {
      throw new BadRequestException(BadRequestException.DEVICE_NAME_MISSING);
    }

    if (StringUtils.isEmpty(key)) {
      throw new BadRequestException(BadRequestException.DEVICE_KEY_MISSING);
    }
    return service.registerDevice(name, key);
  }

  @PUT
  @Override
  @Path("/updateStatus")
  @Produces(MediaType.APPLICATION_JSON)
  public Device updateStatus(@QueryParam("key") final String key,
      @QueryParam("status") final String status) throws Exception {

    logger.info("Key:" + key);
    logger.info("Status:" + status);

    if (StringUtils.isEmpty(status)) {
      throw new InvalidActivityException("Please provide valid stauts:" + status);
    }
    DeviceManagerStatus managerStatus = null;

    switch (status.toUpperCase()) {
      case "OK":
        managerStatus = DeviceManagerStatus.OK;
        break;
      case "UNHEALTHY":
        managerStatus = DeviceManagerStatus.UNHEALTHY;
        break;
      default:
        throw new InvalidActivityException("Please provide valid stauts as OK or UNHEALTHY");
    }
    return service.updateStatus(key, managerStatus);
  }

  @GET
  @Override
  @Path("/list")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Device> getAllDevice() {
    return Collections.unmodifiableList(converter.convert(service.listDevice()));
  }


  @GET
  @Override
  @Path("/getDevice")
  @Produces(MediaType.APPLICATION_JSON)
  public Device getDevice(@QueryParam("name") final String name) throws Exception {
    return service.getDeviceDetails(name);
  }

  @GET
  @Override
  @Path("/getDeviceByStatus")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Device> getDeviceByStatus(@QueryParam("status") final String status)
      throws Exception {

    logger.info("Status:" + status);
    if (StringUtils.isEmpty(status)) {
      throw new InvalidActivityException("Please provide valid stauts:" + status);
    }

    DeviceManagerStatus managerStatus = null;
    switch (status.toUpperCase()) {
      case "NEW":
        managerStatus = DeviceManagerStatus.NEW;
        break;
      case "OK":
        managerStatus = DeviceManagerStatus.OK;
        break;
      case "UNHEALTHY":
        managerStatus = DeviceManagerStatus.UNHEALTHY;
        break;
      case "STALE":
        managerStatus = DeviceManagerStatus.STALE;
        break;
      default:
        throw new InvalidActivityException(
            "Please provide valid stauts as  NEW, OK, UNHEALTHY, STALE");
    }
    return Collections.unmodifiableList(converter.convert( service.listDeviceByStatus(managerStatus))
       );
  }
}
