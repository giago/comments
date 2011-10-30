package org.chickymate.client.model;

import java.util.Date;

public class CommentDTO extends VotableDTO {

	private Long id;
	private String url;
	private String host;
	private String text;
	private Date createdDate;
	private String username;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		if(username == null) {
			username = "Anonymous";
		}
		return username;
	}
}
