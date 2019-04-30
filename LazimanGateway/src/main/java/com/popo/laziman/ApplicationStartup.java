package com.popo.laziman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.popo.laziman.cloud.iot.CloudConfig;
import com.popo.laziman.gateway.model.IotParameter;
import com.popo.laziman.gateway.model.IotParameter.IotParameterType;
import com.popo.laziman.gateway.repository.IotParameterRepository;
import com.popo.laziman.service.GatewayService;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private IotParameterRepository iotParameterRepository;
	@Autowired
	private GatewayService gatewayService;

	/**
	 * This event is executed as late as conceivably possible to indicate that
	 * the application is ready to service requests.
	 */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		try {
			System.out.println("Loading configuration");
			loadCloudConfig();
			gatewayService.initial();
			gatewayService.listenForConfigMessages();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}

	private void loadCloudConfig() {
		try {

			IotParameter projectIdParameter = iotParameterRepository.findByParameterType(IotParameterType.projectId);
			IotParameter cloudRegionParameter = iotParameterRepository.findByParameterType(IotParameterType.cloudRegion);
			IotParameter registryIdParameter = iotParameterRepository.findByParameterType(IotParameterType.registryId);
			IotParameter privateKeyParameter = iotParameterRepository.findByParameterType(IotParameterType.privatekeyFile);
			IotParameter algorithmParameter = iotParameterRepository.findByParameterType(IotParameterType.algorithm);
			IotParameter mqttBridgeHostParameter = iotParameterRepository.findByParameterType(IotParameterType.mqttBridgeHost);
			IotParameter mqttBridgePortParameter = iotParameterRepository.findByParameterType(IotParameterType.mqttBridgePort);
			IotParameter gatewayIdParameter = iotParameterRepository.findByParameterType(IotParameterType.gatewayId);
			IotParameter serviceAccountParameter = iotParameterRepository.findByParameterType(IotParameterType.serviceAccount);
			IotParameter deviceControlTopicParameter = iotParameterRepository.findByParameterType(IotParameterType.deviceControlTopic);

			if (projectIdParameter == null) {
				projectIdParameter = new IotParameter(IotParameterType.projectId, "smartmanipulator");
				iotParameterRepository.save(projectIdParameter);
			}
			if (cloudRegionParameter == null) {
				cloudRegionParameter = new IotParameter(IotParameterType.cloudRegion, "asia-east1");
				iotParameterRepository.save(cloudRegionParameter);
			}
			if (registryIdParameter == null) {
				registryIdParameter = new IotParameter(IotParameterType.registryId, "my-registry");
				iotParameterRepository.save(registryIdParameter);
			}
			if (privateKeyParameter == null) {
				privateKeyParameter = new IotParameter(IotParameterType.privatekeyFile, "rsa_private_pkcs8");
				iotParameterRepository.save(privateKeyParameter);
			}
			if (algorithmParameter == null) {
				algorithmParameter = new IotParameter(IotParameterType.algorithm, "RS256");
				iotParameterRepository.save(algorithmParameter);
			}
			if (mqttBridgeHostParameter == null) {
				mqttBridgeHostParameter = new IotParameter(IotParameterType.mqttBridgeHost, "mqtt.googleapis.com");
				iotParameterRepository.save(mqttBridgeHostParameter);
			}
			if (mqttBridgePortParameter == null) {
				mqttBridgePortParameter = new IotParameter(IotParameterType.mqttBridgePort, "8883");
				iotParameterRepository.save(mqttBridgePortParameter);
			}
			if (gatewayIdParameter == null) {
				gatewayIdParameter = new IotParameter(IotParameterType.gatewayId, "my-gateway");
				iotParameterRepository.save(gatewayIdParameter);
			}
			if (serviceAccountParameter == null) {
				serviceAccountParameter = new IotParameter(IotParameterType.serviceAccount, "smartmanipulator@appspot.gserviceaccount.com");
				iotParameterRepository.save(serviceAccountParameter);
			}

			if (deviceControlTopicParameter == null) {
				deviceControlTopicParameter = new IotParameter(IotParameterType.deviceControlTopic, "device-control");
				iotParameterRepository.save(deviceControlTopicParameter);
			}

			CloudConfig.projectId = projectIdParameter.getParameterValue();
			CloudConfig.cloudRegion = cloudRegionParameter.getParameterValue();
			CloudConfig.registryId = registryIdParameter.getParameterValue();
			String privateKey = privateKeyParameter.getParameterValue();
			CloudConfig.privateKeyFile = new ClassPathResource(privateKey).getFile().getAbsolutePath();
			CloudConfig.algorithm = algorithmParameter.getParameterValue();

			CloudConfig.mqttBridgeHost = mqttBridgeHostParameter.getParameterValue();
			CloudConfig.mqttBridgePort = Integer.parseInt(mqttBridgePortParameter.getParameterValue());

			CloudConfig.gatewayId = gatewayIdParameter.getParameterValue();

			CloudConfig.serviceAccount = serviceAccountParameter.getParameterValue();
			CloudConfig.deviceControlTopic = deviceControlTopicParameter.getParameterValue();

			BufferedReader r = new BufferedReader(new InputStreamReader(new ClassPathResource("smartmanipulator-9299ef822e64.json").getInputStream(), "UTF-8"));
			StringBuilder jsonStringBuilder = new StringBuilder();
			for (String line; (line = r.readLine()) != null;) {
				jsonStringBuilder.append(line).append('\n');
			}

			CloudConfig.googleAPICredential = jsonStringBuilder.toString();

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
