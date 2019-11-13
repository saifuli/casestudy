package com.casestudy.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.casestudy.model.Authorities;

@Entity
@Table(name = "credentials")
public class Credential {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Valid
	@Column(name = "username", nullable = false)
	@NotEmpty (message = "username must not be empty")
	private String username;
	
	@Valid
	@Column(name = "password", nullable = false)
	@NotEmpty (message = "password must not be empty")
	private String password;
	
	@Valid
	@Column(name = "email", unique = true, nullable = false)
	@Email
	@NotEmpty (message = "email must not be empty")
	private String email;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "credential")
	@Column(name = "authority")
	private Set<Authorities> authorities = new HashSet<Authorities>();

	@Column(name = "join_date")
	private Date joinDate;

	@Valid
	@OneToOne(mappedBy = "credential", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;

	public Credential() {
	}

	public Credential(@Email String email, String username, String password, Set<Authorities> authorities,
			Date joinDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
		this.joinDate = joinDate;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}

//	@Override
//	public String toString() {
//		return "Credential [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
//				+ ", authorities=" + authorities + ", joinDate=" + joinDate + ", user=" + user + "]";
//	}

//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
//	}

}
