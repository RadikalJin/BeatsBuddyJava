package com.joe.task.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "TASK")
public class Task implements java.io.Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "TITLE", nullable = true)
	private String title;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	@Column(name = "DUE_DATE", nullable = true)
	private Calendar dueDate;

	public Task() {
		super();
	}

	public Task(String title, String description, Calendar dueDate) {
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Task task = (Task) o;

		if (description != null ? !description.equals(task.description) : task.description != null) return false;
		if (dueDate != null ? !dueDate.equals(task.dueDate) : task.dueDate != null) return false;
		if (title != null ? !title.equals(task.title) : task.title != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = title != null ? title.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
		return result;
	}
}
