package com.namelessproject.user;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "USER")
@Access(AccessType.FIELD)
public class UserEntity {

	@Id
	@TableGenerator(name = "USER_ID_GEN", 	
					table = "ID_GENERATOR", 
					pkColumnName = "SEQ_NAME", 
					pkColumnValue = "USER", 
					valueColumnName = "SEQ_VALUE")
	@GeneratedValue(generator = "USER_ID_GEN", 
					strategy = GenerationType.TABLE)
	private Long id;
	@Column(unique = true)
	@NotBlank(message = "user.username.cannot.be.empty")
	private String username;
	@Column(unique = true)
	@NotBlank(message = "user.email.cannot.be.empty")
	private String email;
	@NotBlank(message = "user.password.cannot.be.empty")
	private String password;
	
	public UserEntity() {
	}

	public UserEntity(Long id, String username, String email, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}