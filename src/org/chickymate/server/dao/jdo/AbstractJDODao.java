package org.chickymate.server.dao.jdo;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.chickymate.server.dao.PMF;
import org.chickymate.server.dao.jdo.query.CommentQueryBuilder;
import org.chickymate.server.dao.jdo.query.HostQueryBuilder;
import org.chickymate.server.dao.jdo.query.ImageQueryBuilder;
import org.chickymate.server.dao.jdo.query.UrlQueryBuilder;
import org.chickymate.server.model.Comment;
import org.chickymate.server.model.Host;
import org.chickymate.server.model.Image;
import org.chickymate.server.model.Url;

public class AbstractJDODao {
	
	protected static CommentQueryBuilder COMMENT_QUERY_BUILDER = new CommentQueryBuilder();
	protected static HostQueryBuilder HOST_QUERY_BUILDER = new HostQueryBuilder();
	protected static UrlQueryBuilder URL_QUERY_BUILDER = new UrlQueryBuilder();
	protected static ImageQueryBuilder IMAGE_QUERY_BUILDER = new ImageQueryBuilder();

	protected PersistenceManager getPM() {
		return PMF.get().getPersistenceManager();
	}
	
	protected Object executeAndClose(Query query) {
		Object result =  query.execute();
		query.closeAll();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Comment> executeAndCloseCommentQuery(Query query){
		return (List<Comment>)executeAndClose(query);
	}

	@SuppressWarnings("unchecked")
	protected List<Image> executeAndCloseImageQuery(Query query){
		return (List<Image>)executeAndClose(query);
	}

	@SuppressWarnings("unchecked")
	protected List<Url> executeAndCloseUrlQuery(Query query){
		return (List<Url>)executeAndClose(query);
	}

	@SuppressWarnings("unchecked")
	protected List<Host> executeAndCloseHostQuery(Query query){
		return (List<Host>)executeAndClose(query);
	}
	
}
