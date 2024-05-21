package com.lacerda.toDoList.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "taks")
public class Tasks {
	

	@ManyToOne
	@JoinColumn(name = "day_id")
	private Days days;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String taks;
	
	public Tasks() {
		
	}

	public Tasks(Days days, Long id, String taks) {
		this.days = days;
		this.id = id;
		this.taks = taks;
	}

	public Days getDays() {
		return days;
	}

	public void setDays(Days days) {
		this.days = days;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaks() {
		return taks;
	}

	public void setTaks(String taks) {
		this.taks = taks;
	}

	@Override
	public String toString() {
		return "Tasks [days=" + days + ", id=" + id + ", taks=" + taks + "]";
	}
	
	
	
}
