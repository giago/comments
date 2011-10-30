package org.chickymate.server.dao;

import java.util.List;

import org.chickymate.server.model.Comment;
import org.chickymate.server.model.Host;
import org.chickymate.server.model.Image;
import org.chickymate.server.model.Url;

public interface CommentDao {

	void addComment(String url, String text, String username);
	
	void vote(long id, int vote);
	
	Boolean hasCommentsForUrl(String url);

	List<Comment> getComments(String url, String orderProperty, long offset, long pageEnd);

	List<Comment> getComments(String orderProperty, long offset, long pageEnd);		

	List<Host> getHosts(String orderProperty, long offset, long pageEnd);		
	
	List<Url> getUrls(String orderProperty, long offset, long pageEnd);
	
	List<Image> getImages(String orderProperty, long offset, long pageEnd);

	
	
}
