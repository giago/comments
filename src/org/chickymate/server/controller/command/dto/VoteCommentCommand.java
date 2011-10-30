package org.chickymate.server.controller.command.dto;

public class VoteCommentCommand  extends DTOAbstractCommand {

	public void execute(Long id, int vote) {
		getCommentDao().vote(id, vote);
	}
	
}
