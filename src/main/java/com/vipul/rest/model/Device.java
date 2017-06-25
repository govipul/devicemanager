package com.vipul.rest.model;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
@Produces("application/json")
@JsonIgnoreProperties({"modified"})
public class Device {

  @XmlElement(name = "name")
  @JsonProperty("name")
  private String name;

  @XmlElement(name = "key")
  @JsonProperty("key")
  private String key;

  @XmlElement(name = "status")
  @JsonProperty("status")
  private DeviceManagerStatus status;

  @JsonIgnore
  private long modified;

  public Device() {
    this.name = null;
    this.key = null;
    this.status = DeviceManagerStatus.NEW;
  }

  public Device(final String name, final String key) {
    this.name = name;
    this.key = !StringUtils.isEmpty(key) ? key.toUpperCase() : key;
    this.status = DeviceManagerStatus.NEW;
    this.modified = System.currentTimeMillis();
  }

  public Device(final String name, final String key, final DeviceManagerStatus status) {
    this.name = name;
    this.key = !StringUtils.isEmpty(key) ? key.toUpperCase() : key;
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key.toUpperCase();
  }

  public DeviceManagerStatus getStatus() {
    return status;
  }

  public void setStatus(DeviceManagerStatus status) {
    this.status = status;
    this.modified = System.currentTimeMillis();
  }

  @JsonIgnore
  public long getModified() {
    return this.modified;
  }

  @Override
  public String toString() {
    return "Device [name=" + name + ", key=" + key + ", status=" + status + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj == null)
      return false;

    if (getClass() != obj.getClass())
      return false;

    Device other = (Device) obj;

    if (key == null) {
      if (other.key != null)
        return false;
    } else if (!key.equals(other.key))
      return false;

    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

}
