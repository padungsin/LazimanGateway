package com.popo.laziman.gateway.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.popo.laziman.cloud.iot.model.CustomDevice;
import com.popo.laziman.cloud.iot.model.DeviceType;

@Entity
@Table(name = "iot_device")
public class IotDevice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 236416830495684177L;
	
	public IotDevice() {
	
	}
	public IotDevice(CustomDevice customDevice) {
		this.deviceId = customDevice.getDeviceId();
		this.deviceName = customDevice.getDeviceName();
		this.deviceType = customDevice.getDeviceType();
	}
	
	public void populate(CustomDevice customDevice) {

		this.deviceName = customDevice.getDeviceName();
		this.deviceType = customDevice.getDeviceType();
	}
	
	public CustomDevice getCustomDevice(){
		CustomDevice device = new CustomDevice();
		device.setDeviceId(this.deviceId);
		device.setDeviceName(this.deviceName);
		device.setDeviceType(this.deviceType);
		
		return device;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String deviceId;
	@Column
	private String deviceName;
	
	@Column
	@Enumerated(EnumType.STRING)
	private DeviceType deviceType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}



}
