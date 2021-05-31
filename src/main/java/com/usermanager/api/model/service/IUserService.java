package com.usermanager.api.model.service;

import com.usermanager.api.view.UserRequestView;
import com.usermanager.api.view.UserResponseView;

public interface IUserService 
{	
	public UserResponseView save(UserRequestView userRequestView) throws Exception;	
}
