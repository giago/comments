package org.chickymate.client;

import org.chickymate.client.model.PageDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("comment")
public interface CommentService extends RemoteService {
	
	PageDTO getHosts(PageDTO page);
	
	PageDTO getComments(PageDTO page);
	
	PageDTO getUrls(PageDTO page);
	
	PageDTO getImages(PageDTO page);

	PageDTO getComments(String url, PageDTO page);
	
	void addComment(String url, String comment, String unsername);
	
	void vote(Long id, int vote);

}
