package com.lacerda.toDoList.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="days")
public class Days implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate days;
	
	@OneToMany(mappedBy = "days")
	private List<Tasks> tasks;
	
	public Days() {
		
	}

	public Days(Long id, LocalDate days) {
		this.id = id;
		this.days = days;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDays() {
		return days;
	}

	public void setDays(LocalDate days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "Days [id=" + id + ", days=" + days + "]";
	}
	
}
