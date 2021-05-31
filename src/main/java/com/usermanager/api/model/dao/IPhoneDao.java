package com.usermanager.api.model.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.usermanager.api.model.entity.Phone;

public interface IPhoneDao extends CrudRepository<Phone, UUID>
{
	
}
