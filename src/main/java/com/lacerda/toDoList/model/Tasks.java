package com.lacerda.toDoList.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Tasks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "task", length = 255)
	private String tasks;
	
	@Column(name = "taksCreatedAt", length = 255)
	private LocalDateTime taskCreatedAt;
	
	@Column(name = "data", length = 255)
	private LocalDate data;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	public Tasks() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTasks() {
		return tasks;
	}

	public void setTasks(String tasks) {
		this.tasks = tasks;
	}

	public LocalDateTime getTaskCreatedAt() {
		return taskCreatedAt;
	}

	public void setTaskCreatedAt(LocalDateTime taskCreatedAt) {
		this.taskCreatedAt = taskCreatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Tasks [id=" + id + ", tasks=" + tasks + ", taskCreatedAt=" + taskCreatedAt + ", data=" + data
				+ ", user=" + user + "]";
	}
		
}
