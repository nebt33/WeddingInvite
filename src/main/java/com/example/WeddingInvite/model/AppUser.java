package com.example.WeddingInvite.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="APP_USER")
@Data
public class AppUser {
	
	@Id
	@Column(name="user_name")
	private String userName;

	@Column
	private String password;

}
