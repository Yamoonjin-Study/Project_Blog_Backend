package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@Column(nullable = false, length = 11)
	private String phone;
	
	@Enumerated(value = EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp
	private Timestamp createDate;

	@Enumerated(value = EnumType.STRING)
	private Status status;

	@Getter
	public enum RoleType{
		USER("USER"),
		ADMIN("ADMIN");

		private final String name;

		RoleType(String name) {
			this.name = name;
		}
	}

	@Getter
	public enum Status{
		ACTIVE("ACTIVE"),
		INACTIVE("INACTIVE");

		private final String name;

		Status(String name) {
			this.name = name;
		}
	}
}

