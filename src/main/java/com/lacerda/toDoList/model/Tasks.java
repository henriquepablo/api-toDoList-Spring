package com.lacerda.toDoList.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tasks")
public class Tasks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String taks;
	
	public Tasks() {
		
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
		return "Tasks [days=" + ", id=" + id + ", taks=" + taks + "]";
	}

	
}
