package org.chickymate.server.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.chickymate.client.model.HostDTO;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Host extends Model<HostDTO> {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String host;
	
	@Persistent private int votes;
	@Persistent private int negativeVotes;
	@Persistent private int positiveVotes;
	@Persistent private Date createdDate;
	
	public Host() {}
	
	public Host(String host) {
		this.host = host;
		this.positiveVotes = 1;
		this.votes = 1;
		this.createdDate = new Date();
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
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
	public HostDTO asDTO() {
		HostDTO host = new HostDTO();
		host.setHost(getHost());
		host.setNegativeVotes(getNegativeVotes());
		host.setPositiveVotes(getPositiveVotes());
		host.setCreatedDate(getCreatedDate());
		return host;
	}

}
