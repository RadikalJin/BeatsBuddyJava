package com.joe.user.domain;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User implements java.io.Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "USERNAME", nullable = false)
	private String username;

	@Column(name = "TITLE", nullable = true)
	private String password;

	public User() {
		super();
	}

	public User(String username, String password){
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User details: {" +
				"Id = " + id +
				", Username = '" + username + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (user != null ? !user.equals(user.username) : user.username != null) return false;
		if (id != null ? !id.equals(user.id) : user.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		return result;
	}
}
