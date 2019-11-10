package com.casestudy.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
//	@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
	@MapsId
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Credential credential;

	@Column(name = "name", nullable = false)
	@NotEmpty
	private String name;
	
	@OneToMany(mappedBy = "author", 
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Post> posts;
	
	@OneToMany(mappedBy = "author", 
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();
	
	@Column(name = "authority_tier")
	private int authorityTier;
	
	@Column(name = "num_of_posts")
	private int numOfPosts;
	
	@Column(name = "num_of_comments")
	private int numOfComments;

	public User() {
		numOfComments = 0;
		numOfPosts = 0;
	}
	
	public User(String name) {
		this.name = name;
		numOfComments = 0;
		numOfPosts = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getAuthorityTier() {
		return authorityTier;
	}

	public void setAuthorityTier(int authorityTier) {
		this.authorityTier = authorityTier;
	}

	public int getNumOfPosts() {
		return numOfPosts;
	}

	public void setNumOfPosts(int numOfPosts) {
		this.numOfPosts = numOfPosts;
	}

	public int getNumOfComments() {
		return numOfComments;
	}

	public void setNumOfComments(int numOfComments) {
		this.numOfComments = numOfComments;
	}
	
//	@Override
//	public String toString() {
//		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
//	}
}
