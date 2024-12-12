package com.springboot.myfirstwebapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String username;

	@Lob
	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private LocalDate targetDate;

	@Column(nullable = false)
	private boolean done;

	public Todo() {
		super();
	}

	public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private int id;
		private String username;
		private String description;
		private LocalDate targetDate;
		private boolean done;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder targetDate(LocalDate targetDate) {
			this.targetDate = targetDate;
			return this;
		}

		public Builder done(boolean done) {
			this.done = done;
			return this;
		}

		public Todo build() {
			return new Todo(id, username, description, targetDate, done);
		}
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", done=" + done + "]";
	}
}
