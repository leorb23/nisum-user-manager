package com.usermanager.api.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable
{
	
	private static final long serialVersionUID = -8171033602562273639L;
	
	@Id	
	private UUID id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String token;
	
	@Column(name = "is_active")
	private boolean isActive;	
		
	//@Temporal(TemporalType.DATE)
	private LocalDate created;	
	
	//@Temporal(TemporalType.DATE)
	private LocalDate modified;
	
	@Column(name = "last_login")
	//@Temporal(TemporalType.DATE)
	private LocalDate lastLogin;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Phone> phones;
	
	public UUID getId() 
	{
		return id;
	}
	public void setId(UUID id) 
	{
		this.id = id;
	}	
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}	
	public boolean isActive() 
	{
		return isActive;
	}
	public void setActive(boolean isActive) 
	{
		this.isActive = isActive;
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
	public List<Phone> getPhones() 
	{
		return phones;
	}
	public void setPhones(List<Phone> phones) 
	{
		this.phones = phones;
	}
	public String getToken() 
	{
		return token;
	}
	public void setToken(String token) 
	{
		this.token = token;
	}
		
}
