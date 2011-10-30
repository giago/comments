package org.chickymate.server.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.chickymate.client.model.CommentDTO;
import org.chickymate.server.util.UrlParser;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Comment extends Model<CommentDTO> {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent private String url;
	@Persistent private String username;
	@Persistent private String host;
	@Persistent private String query;
	@Persistent private String path;
	@Persistent private String text;
	@Persistent private Date createdDate;
	@Persistent private int votes;
	@Persistent private int negativeVotes;
	@Persistent private int positiveVotes;

	public Comment() {}

	public Comment(String url, String text, String username) {
		super();
		this.url = url;
		UrlParser parser = new UrlParser(url);
		this.host = parser.getHost();
		this.query = parser.getQuery();
		this.path = parser.getPath();
		this.text = text;
		this.createdDate = new Date();
		this.votes = 1;
		this.positiveVotes = 1;
		this.negativeVotes = 0;
		if(username!=null) {
			username = username.trim();
			if(username.isEmpty()) {
				username = null;
			}
		}
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setLong(Long id) {
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public int getNegativeVotes() {
		return negativeVotes;
	}

	public void setNegativeVotes(int negativeVotes) {
		this.negativeVotes = negativeVotes;
	}

	public int getPositiveVotes() {
		return positiveVotes;
	}
	
	public void setPositiveVotes(int positiveVotes) {
		this.positiveVotes = positiveVotes;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void addVote(int vote) {
		if(vote > 0) {
			this.positiveVotes++;
		} else if (vote < 0){
			this.negativeVotes++;
		}
		this.votes++;
	}

	@Override
	public CommentDTO asDTO() {
		CommentDTO comment = new CommentDTO();
		comment.setCreatedDate(getCreatedDate());
		comment.setId(getId());
		comment.setNegativeVotes(getNegativeVotes());
		comment.setPositiveVotes(getPositiveVotes());
		comment.setText(getText());
		comment.setUrl(getUrl());
		comment.setUsername(username);
		return comment;
	}

}
