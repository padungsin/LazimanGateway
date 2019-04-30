package com.popo.laziman.gateway.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.popo.laziman.gateway.model.IotParameter;
import com.popo.laziman.gateway.model.IotParameter.IotParameterType;

@Transactional
public interface IotParameterRepository extends CrudRepository<IotParameter, Long>{
	public IotParameter findByParameterType(IotParameterType parameterType);
}
