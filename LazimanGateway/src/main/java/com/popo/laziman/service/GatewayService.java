package com.popo.laziman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popo.laziman.cloud.iot.CloudConfig;
import com.popo.laziman.cloud.iot.DeviceRegistryImpl;
import com.popo.laziman.cloud.iot.MqttGatewatImpl;
import com.popo.laziman.cloud.iot.model.CustomDevice;
import com.popo.laziman.cloud.iot.model.CustomGateway;
import com.popo.laziman.gateway.model.IotDevice;
import com.popo.laziman.gateway.model.IotGateway;
import com.popo.laziman.gateway.repository.IotDeviceRepository;
import com.popo.laziman.gateway.repository.IotGatewayRepository;
import com.popo.laziman.gateway.repository.IotTopicRepository;
import com.popo.laziman.iot.callback.DeviceCallback;

@Service
public class GatewayService {

	@Autowired
	private IotTopicRepository iotTopicRepository;
	@Autowired
	private IotDeviceRepository iotDeviceRepository;
	
	@Autowired
	private IotGatewayRepository iotGatewayRepository;
	

	
	public boolean initial() throws Exception{

		
		IotGateway iotGateway = iotGatewayRepository.findByGatewayId(CloudConfig.gatewayId);
		if(iotGateway == null){
			CustomGateway customGateway = DeviceRegistryImpl.getGateway(CloudConfig.gatewayId, CloudConfig.projectId, CloudConfig.cloudRegion, CloudConfig.registryId);
			iotGateway =new IotGateway(customGateway);
			iotGateway.populate(customGateway);
			iotGatewayRepository.save(iotGateway);
		}
		
		
		
		List<CustomDevice> devices = DeviceRegistryImpl.listDevicesForGateway(CloudConfig.projectId, CloudConfig.cloudRegion, CloudConfig.registryId, CloudConfig.gatewayId);
		for (CustomDevice customDevice : devices) {
			IotDevice device = iotDeviceRepository.findByDeviceId(customDevice.getDeviceId());
			if(device == null){
				device = new IotDevice(customDevice);
			}else{
				device.populate(customDevice);
			}
			
			iotDeviceRepository.save(device);

		}
		return true;
	}
	
	public boolean listenForConfigMessages() throws Exception{
		
		IotGateway iotGateway = iotGatewayRepository.findByGatewayId(CloudConfig.gatewayId);
		CustomGateway gateway = iotGateway.getCustomGateway();
		
		List<IotDevice> iotDevices = iotDeviceRepository.findAll();
		List<CustomDevice> devices = new ArrayList<>();
		
		boolean found = false;
		for (IotDevice iotDevice : iotDevices) {
			if(!found){
				devices.add(iotDevice.getCustomDevice());
				//found = true;
			}
		}
		//List<CustomDevice> devices = DeviceRegistryImpl.listDevicesForGateway(CloudConfig.projectId, CloudConfig.cloudRegion, CloudConfig.registryId, gateway.getGatewayId());
		
		MqttGatewatImpl.getInstance(gateway, new DeviceCallback()).listenForConfigMessages(devices);
		
		
		//MqttGatewatImpl.getInstance(gateway, new DeviceCallback()).listenForEvent();
		
		return true;
	}
}
