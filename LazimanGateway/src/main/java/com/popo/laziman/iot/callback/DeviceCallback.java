package com.popo.laziman.iot.callback;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popo.laziman.cloud.iot.model.Command;
import com.popo.laziman.cloud.iot.model.State;
import com.popo.laziman.service.DeviceService;
import com.popo.laziman.util.GsonUtil;

@Component
public class DeviceCallback implements MqttCallback {

	@Autowired
	private DeviceService deviceService;

	@Override
	public void connectionLost(Throwable cause) {
		// Do nothing...
	}

	@Override
	public void messageArrived(String topic, MqttMessage message){
		try {
			String payload = new String(message.getPayload());

			System.out.println("DeviceCallback From topic: " + topic);
			System.out.println("DeviceCallback Payload : " + payload);

			if (topic.endsWith("commands")) {
				// send command to device;

				// update state
				Command command = (Command) GsonUtil.toObject(payload, Command.class);
				State state = new State(command);
				deviceService.sendDeviceState(state);

			} else {

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Gson gson = new
		// GsonBuilder().enableComplexMapKeySerialization().serializeNulls().setDateFormat("yyyy-MM-dd
		// HH:mm:ss").setPrettyPrinting().setVersion(1.0).create();
		/*
		 * try { Command command = gson.fromJson(payload, Command.class); }
		 * catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
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
