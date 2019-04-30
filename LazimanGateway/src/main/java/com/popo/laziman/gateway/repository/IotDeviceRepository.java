package com.popo.laziman.gateway.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.popo.laziman.gateway.model.IotDevice;

@Transactional
public interface IotDeviceRepository extends CrudRepository<IotDevice, Long> {
	public IotDevice findByDeviceId(String deviceId);
	public List<IotDevice> findAll();
}
