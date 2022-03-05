package com.martiniriarte.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Buy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfPurchase;

	@ManyToOne
	private User owner;

	public Buy(User owner) {
		super();
		this.owner = owner;
	}
}
