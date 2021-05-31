package com.usermanager.api.view;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UserResponseView 
{
	private UUID id;
	private LocalDate created;
	private LocalDate modified;
	private LocalDate lastLogin;
	//private String token;
	private String token;
	private boolean isActive;
	private List<PhoneResponseView> phones;
	
	public UUID getId() 
	{
		return id;
	}
	public void setId(UUID id) 
	{
		this.id = id;
	}
	public LocalDate getCreated() 
	{
		return created;
	}
	public void setCreated(LocalDate created) 
	{
		this.created = created;
	}
	public LocalDate getModified() 
	{
		return modified;
	}
	public void setModified(LocalDate modified) 
	{
		this.modified = modified;
	}
	public LocalDate getLastLogin() 
	{
		return lastLogin;
	}
	public void setLastLogin(LocalDate lastLogin) 
	{
		this.lastLogin = lastLogin;
	}
	
	public String getToken() 
	{
		return token;
	}
	public void setToken(String token) 
	{
		this.token = token;
	}
	public boolean isActive() 
	{
		return isActive;
	}
	public void setActive(boolean isActive) 
	{
		this.isActive = isActive;
	}
	public List<PhoneResponseView> getPhones() 
	{
		return phones;
	}
	public void setPhones(List<PhoneResponseView> phones) 
	{
		this.phones = phones;
	}
	
}
