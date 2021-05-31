package com.usermanager.api.view;

import java.util.UUID;

public class PhoneResponseView 
{
	private UUID id;
	private Long number;
	private Long cityCode;
	private Long contryCode;
	
	public UUID getId() 
	{
		return id;
	}
	public void setId(UUID id) 
	{
		this.id = id;
	}
	public Long getNumber() 
	{
		return number;
	}
	public void setNumber(Long number) 
	{
		this.number = number;
	}
	public Long getCityCode() {
		return cityCode;
	}
	public void setCityCode(Long cityCode) 
	{
		this.cityCode = cityCode;
	}
	public Long getContryCode() 
	{
		return contryCode;
	}
	public void setContryCode(Long contryCode) 
	{
		this.contryCode = contryCode;
	}	
}
