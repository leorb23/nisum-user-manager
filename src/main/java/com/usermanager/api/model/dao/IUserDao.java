package com.usermanager.api.model.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.usermanager.api.model.entity.User;

public interface IUserDao extends CrudRepository<User, UUID>{

	public User findByEmail(String email);
	
}
