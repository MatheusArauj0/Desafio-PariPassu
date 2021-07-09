package com.example.gerenciadorsenhasbancarias.entities;

import java.io.Serializable;

public class PasswordDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer number;
	private Boolean pritorary;
	private String pass;

	public PasswordDTO() {

	}

	public PasswordDTO(Long id, Integer number, Boolean pritorary, String pass) {
		this.id = id;
		this.number = number;
		this.pritorary = pritorary;
		this.pass = pass;
	}

	public PasswordDTO(Password entity) {
		id = entity.getId();
		number = entity.getNumber();
		pritorary = entity.getPriority();
		pass = entity.getPass();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getPritorary() {
		return pritorary;
	}

	public void setPritorary(Boolean pritorary) {
		this.pritorary = pritorary;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

}
