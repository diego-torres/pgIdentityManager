package com.dtorres.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@TypeDefs({ @TypeDef(name="chkpass", typeClass=com.dtorres.customTypes.Chkpass.class) })
@Entity
@Table(name="idm_user", uniqueConstraints = @UniqueConstraint(columnNames = { "user_name" }))
public class User {
	@Id
	@Column(name="user_id", insertable=false, updatable=false)
	@SequenceGenerator(name = "user_id_seq", sequenceName = "idm_user_user_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	private long userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password", columnDefinition="chkpass", updatable=false, nullable=false)
	@Type(type = "chkpass")
	private String password;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
