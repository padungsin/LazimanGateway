package com.popo.laziman.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.popo.laziman.cloud.iot.model.Command;
import com.popo.laziman.cloud.iot.model.State;
import com.popo.laziman.service.DeviceService;

@RestController
public class DeviceController {
	@Autowired
	private DeviceService deviceService;

	@RequestMapping(value = "device/state")
	public boolean sendState(@RequestBody State state) {

		try {
			state.setLastUpdate(new Date());
			return deviceService.sendDeviceState(state);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	@RequestMapping(value = "device/command")
	public boolean sendCommand(@RequestBody Command command) {
		try {
			
			
			return deviceService.sendCommand(command);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
