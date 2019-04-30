package com.popo.laziman.iot.callback;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popo.laziman.cloud.iot.model.Command;
import com.popo.laziman.cloud.iot.model.CustomDevice;

public class DeviceCallback implements MqttCallback {

	private CustomDevice device;

	@Override
	public void connectionLost(Throwable cause) {
		// Do nothing...
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		String payload = new String(message.getPayload());

		System.out.println("DeviceCallback From topic: " + topic);
		System.out.println("DeviceCallback Payload : " + payload);

		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").setPrettyPrinting().setVersion(1.0).create();

		try {
			Command command = gson.fromJson(payload, Command.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * if (topic.endsWith("/commands")) { State state = new State();
		 * state.setDeviceId(device.getDeviceId());
		 * 
		 * if(command.getControl().equals(ControlType.on)){
		 * state.setState(DeviceState.off); }else{
		 * state.setState(DeviceState.off); }
		 * 
		 * }
		 */

		// TODO: Insert your parsing / handling of the configuration
		// message here.

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// Do nothing;

	}

}
