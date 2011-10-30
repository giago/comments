package org.chickymate.server;

import org.chickymate.client.CommentService;
import org.chickymate.client.model.PageDTO;
import org.chickymate.server.controller.command.dto.AddCommentCommand;
import org.chickymate.server.controller.command.dto.GetPageOfComment;
import org.chickymate.server.controller.command.dto.GetResourcesCommand;
import org.chickymate.server.controller.command.dto.VoteCommentCommand;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class CommentServiceImpl extends RemoteServiceServlet implements CommentService {

	private static final long serialVersionUID = 1L;
	
	private GetResourcesCommand resourcesCommand = new GetResourcesCommand();

	@Override
	public PageDTO getComments(String url, PageDTO page) {
		return new GetPageOfComment().execute(url, page);
	}

	@Override
	public void vote(Long id, int vote) {
		new VoteCommentCommand().execute(id, vote);
	}

	@Override
	public void addComment(String url, String comment, String username) {
		new AddCommentCommand().execute(url, comment, username);
	}
	
	@Override
	public PageDTO getComments(PageDTO page) {
		return resourcesCommand.getComments(page);
	}

	@Override
	public PageDTO getHosts(PageDTO page) {
		return resourcesCommand.getHosts(page);
	}
	
	@Override
	public PageDTO getUrls(PageDTO page) {
		return resourcesCommand.getUrls(page);
	}
	
	@Override
	public PageDTO getImages(PageDTO page) {
		return resourcesCommand.getImages(page);
	}
	
}
