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

@Entity
@Table(name = "iot_parameter")
public class IotParameter implements Serializable {
	public static enum IotParameterType{
		projectId,cloudRegion,registryId,privatekeyFile,algorithm,mqttBridgeHost,mqttBridgePort,gatewayId,serviceAccount,deviceControlTopic
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8051644773319322479L;
	
	public IotParameter(){
		
	}
	
	public IotParameter(IotParameterType parameterType, String parameterValue ) {
		this.parameterType = parameterType;
		this.parameterValue = parameterValue;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	
	@Column(unique=true, nullable= false)
	@Enumerated(EnumType.STRING)
	private IotParameterType parameterType;
	
	@Column
	private String parameterValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IotParameterType getParameterType() {
		return parameterType;
	}

	public void setParameterType(IotParameterType parameterType) {
		this.parameterType = parameterType;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	
		
	
	

}
