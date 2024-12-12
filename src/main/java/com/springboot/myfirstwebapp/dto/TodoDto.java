package com.springboot.myfirstwebapp.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TodoDto {
	private int id;
	private String username;

	@Size(min = 5, message = "Enter at least 5 characters")
	private String description;

	@NotNull(message = "Target date is mandatory")
	private LocalDate targetDate;
	private boolean done;

	public TodoDto() {
	}

	public TodoDto(int id, String username, String description, LocalDate targetDate, boolean done) {
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

		public TodoDto build() {
			return new TodoDto(id, username, description, targetDate, done);
		}
	}

	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", done=" + done + "]";
	}
}
