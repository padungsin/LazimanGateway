package com.popo.laziman.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	/*
	 * @Scheduled(fixedRate = 2000) public void scheduleTaskWithFixedRate() {
	 * logger.info("Fixed Rate Task :: Execution Time - {}",
	 * dateTimeFormatter.format(LocalDateTime.now())); }
	 */

	@Scheduled(fixedDelay =120000)
	public void scheduleTaskWithFixedDelay() {
		try {
			//DeviceRegistryImpl.createIotTopic(CloudConfig.projectId, "test-topic");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
