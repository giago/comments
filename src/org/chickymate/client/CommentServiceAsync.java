package org.chickymate.client;

import org.chickymate.client.model.PageDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CommentServiceAsync {

	void getHosts(PageDTO page, AsyncCallback<PageDTO> callback);
	
	void getUrls(PageDTO page, AsyncCallback<PageDTO> callback);
	
	void getComments(PageDTO page, AsyncCallback<PageDTO> callback);
	
	void getImages(PageDTO page, AsyncCallback<PageDTO> callback);

	void addComment(String url, String comment, String username, AsyncCallback<Void> asyncCallback);
	
	void vote(Long id, int vote, AsyncCallback<Void> callback);
	
	void getComments(String url, PageDTO page, AsyncCallback<PageDTO> callback);

}
