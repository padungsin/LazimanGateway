package com.popo.laziman.gateway.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.popo.laziman.cloud.iot.model.CustomDevice;
import com.popo.laziman.cloud.iot.model.CustomGateway;

@Entity
@Table(name = "iot_gateway")
public class IotGateway implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5944108463363520857L;

	public IotGateway() {

	}

	public IotGateway(CustomGateway customGateway) {
		this.gatewayId = customGateway.getGatewayId();
		this.gatewayName = customGateway.getGatewayName();

	}

	public void populate(CustomGateway customGateway) {

		this.gatewayId = customGateway.getGatewayId();
		this.gatewayName = customGateway.getGatewayName();
	}

	public CustomGateway getCustomGateway() {

		CustomGateway customGateway = new CustomGateway();

		customGateway.setGatewayId(this.gatewayId);
		customGateway.setGatewayName(this.gatewayName);
		customGateway.setGatewayDevice(new CustomDevice());

		customGateway.getGatewayDevice().setDeviceId(this.gatewayId);
		customGateway.getGatewayDevice().setDeviceName(this.gatewayName);
		
		return customGateway;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String gatewayId;
	@Column
	private String gatewayName;

}
