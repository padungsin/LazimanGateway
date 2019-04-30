package com.popo.laziman.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popo.laziman.cloud.iot.CloudConfig;
import com.popo.laziman.cloud.iot.DeviceRegistryImpl;
import com.popo.laziman.cloud.iot.MqttGatewatImpl;
import com.popo.laziman.cloud.iot.model.Command;
import com.popo.laziman.cloud.iot.model.CustomDevice;
import com.popo.laziman.cloud.iot.model.CustomGateway;
import com.popo.laziman.cloud.iot.model.State;
import com.popo.laziman.gateway.model.IotDevice;
import com.popo.laziman.gateway.model.IotGateway;
import com.popo.laziman.gateway.repository.IotDeviceRepository;
import com.popo.laziman.gateway.repository.IotGatewayRepository;
import com.popo.laziman.iot.callback.DeviceCallback;
import com.popo.laziman.util.GsonUtil;

@Service
public class DeviceService {
	@Autowired
	private IotDeviceRepository iotDeviceRepository;

	
	@Autowired
	private IotGatewayRepository iotGatewayRepository;
	

	public boolean sendDeviceState(State state) {
		try {
			
			IotGateway iotGateway = iotGatewayRepository.findByGatewayId(CloudConfig.gatewayId);
			CustomGateway gateway = iotGateway.getCustomGateway();
			
			IotDevice iotDevice = iotDeviceRepository.findByDeviceId(state.getDeviceId());
			CustomDevice device = iotDevice.getCustomDevice();

			
			
			
			MqttGatewatImpl.getInstance(gateway, new DeviceCallback()).sendDataFromBoundDevice(device, "state",GsonUtil.toJson(state));
					//CloudConfig.mqttBridgeHost, CloudConfig.mqttBridgePort, CloudConfig.projectId, CloudConfig.cloudRegion, CloudConfig.registryId, iotGateway, CloudConfig.privateKeyFile, CloudConfig.algorithm, iotDeviceRepository.findByDeviceId(deviceId).getCustomDevice(), "state", state, callback);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean sendCommand(Command command) {

		Gson gson = new GsonBuilder()
			     .enableComplexMapKeySerialization()
			     .serializeNulls()
			     .setDateFormat("yyyy-MM-dd HH:mm:ss")
			     .setPrettyPrinting()
			     .setVersion(1.0)
			     .create();
		
		System.out.println(gson.toJson(command));
		try {
			
			//DeviceRegistryImpl.setDeviceConfiguration(command.getDeviceId(), CloudConfig.projectId, CloudConfig.cloudRegion, CloudConfig.registryId, gson.toJson(command), 5);
			DeviceRegistryImpl.sendCommand(command.getDeviceId(), CloudConfig.projectId, CloudConfig.cloudRegion, CloudConfig.registryId, gson.toJson(command));
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}
