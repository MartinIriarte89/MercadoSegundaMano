package com.martiniriarte.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lastName;
	
	private String urlAvatar;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date dischargeDate;

	@NotEmpty
	@Column(unique = true)
	private String email;
	
	@NotEmpty
	private String password;

	public User(String name, String lastName, String urlAvatar, String email, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.urlAvatar = urlAvatar;
		this.email = email;
		this.password = password;
	}
}
