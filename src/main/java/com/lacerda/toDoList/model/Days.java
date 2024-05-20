package com.lacerda.toDoList.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="days")
public class Days implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date days;
	
	public Days() {
		
	}

	public Days(Long id, Date days) {
		this.id = id;
		this.days = days;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDays() {
		return days;
	}

	public void setDays(Date days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "Days [id=" + id + ", days=" + days + "]";
	}
	
}
