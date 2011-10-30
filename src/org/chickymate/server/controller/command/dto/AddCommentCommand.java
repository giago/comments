package org.chickymate.server.controller.command.dto;


public class AddCommentCommand extends DTOAbstractCommand {

	public void execute(String url, String comment, String username) {
		getCommentDao().addComment(url, comment, username);
	}
}
