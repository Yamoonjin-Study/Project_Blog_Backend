package com.woonjin.blog.domain.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "blog")
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = true, length = 30, unique = true)
	private String blogname;
	
	@Column(nullable = true, length = 30, unique = true)
	private String nickname;
	
	@Column(nullable = true, length = 50)
	private String info;
	
	@Column(nullable = true, length = 100)
	private String icon;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@Column(nullable = false, length = 1)
	private String status;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", foreignKey = @ForeignKey(name = "fk_user_blog_id"))
	private User user;
}
