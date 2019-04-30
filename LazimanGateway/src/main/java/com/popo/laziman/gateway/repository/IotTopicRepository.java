package com.popo.laziman.gateway.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.popo.laziman.gateway.model.IotTopic;
import com.popo.laziman.gateway.model.IotTopic.TopicType;

@Transactional
public interface IotTopicRepository extends CrudRepository<IotTopic, Long>{
	public IotTopic findByTopicType(TopicType topicType);
}
