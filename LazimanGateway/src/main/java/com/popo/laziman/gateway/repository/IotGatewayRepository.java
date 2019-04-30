package com.popo.laziman.gateway.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.popo.laziman.gateway.model.IotDevice;
import com.popo.laziman.gateway.model.IotGateway;

@Transactional
public interface IotGatewayRepository extends CrudRepository<IotGateway, Long> {
	public IotGateway findByGatewayId(String gatewayId);
	public List<IotGateway> findAll();
}
