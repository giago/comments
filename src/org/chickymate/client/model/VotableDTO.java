package org.chickymate.client.model;

import java.util.Date;

public abstract class VotableDTO extends ModelDTO {

	private int votes;
	private int negativeVotes;
	private int positiveVotes;
	private Date createdDate;
	
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getVotes() {
		return votes;
	}
	public void setNegativeVotes(int negativeVotes) {
		this.negativeVotes = negativeVotes;
	}
	public int getNegativeVotes() {
		return negativeVotes;
	}
	public void setPositiveVotes(int positiveVotes) {
		this.positiveVotes = positiveVotes;
	}
	public int getPositiveVotes() {
		return positiveVotes;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	
}
