package com.popo.laziman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.popo.laziman.service.GatewayService;

@RestController
public class MainController {
	@Autowired
	private GatewayService gatewayService;

	@RequestMapping(value = "initial")
	public boolean getDateAndTime() {

		try {
			return gatewayService.initial();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
