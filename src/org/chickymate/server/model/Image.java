package org.chickymate.server.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.chickymate.client.model.ImageDTO;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Image extends Model<ImageDTO> {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String imageUrl;
	
	@Persistent private int votes;
	@Persistent private int negativeVotes;
	@Persistent private int positiveVotes;
	@Persistent private Date createdDate;

	public Image(){}
	
	public Image(String url){
		this.imageUrl = url;
		this.positiveVotes = 1;
		this.votes = 1;
		this.createdDate = new Date();
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	public ImageDTO asDTO() {
		ImageDTO image = new ImageDTO();
		image.setImageUrl(getImageUrl());
		image.setNegativeVotes(getNegativeVotes());
		image.setPositiveVotes(getPositiveVotes());
		image.setCreatedDate(getCreatedDate());
		return image;
	}
}
