package org.chickymate.server.dao.jdo;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.chickymate.server.dao.CommentDao;
import org.chickymate.server.model.Comment;
import org.chickymate.server.model.Host;
import org.chickymate.server.model.Image;
import org.chickymate.server.model.Url;
import org.chickymate.server.util.ResourceAnalyzer;

/**
 * We need to split this up in more dao.. is growing too much
 * @author luigi.agosti
 *
 */
public class CommentDaoImpl extends AbstractJDODao implements CommentDao {

	@Override
	public void addComment(String url, String text, String username) {
		PersistenceManager pm = getPM();
		Comment comment = new Comment(url, text, username);
		pm.makePersistent(comment);
		updateTables(pm, comment, 1);
		pm.close();
	}

	@Override
	public void vote(long id, int vote) {
		PersistenceManager pm = getPM();
		Comment comment = getComment(pm, id);
		comment.addVote(vote);
		updateTables(pm, comment, vote);
		pm.close();
	}
	
	@Override
	public Boolean hasCommentsForUrl(String url) {
		boolean hasComment = false;
		Query query = COMMENT_QUERY_BUILDER.getQueryWithUrlFilter();
		query.setRange(0,1);
		List<Comment> result = executeAndClose(query, url);
		if(result.size() > 0){
			hasComment = true;
		}
		return hasComment;
	}
	
	@Override
	public List<Comment> getComments(String url, String orderProperty, long offset, long pageEnd) {
		Query query = COMMENT_QUERY_BUILDER.getQueryWithUrlFilter(orderProperty, offset, pageEnd);
		return executeAndClose(query, url);
	}
	
	@Override
	public List<Comment> getComments(String orderProperty, long offset, long pageEnd) {
		Query query = COMMENT_QUERY_BUILDER.getQuery(orderProperty, offset, pageEnd);
		return executeAndCloseCommentQuery(query);
	}
 
	@Override
	public List<Host> getHosts(String orderProperty, long offset, long pageEnd) {
		Query query = HOST_QUERY_BUILDER.getQuery(orderProperty, offset, pageEnd);
		return executeAndCloseHostQuery(query);
	}

	@Override
	public List<Url> getUrls(String orderProperty, long offset, long pageEnd) {
		Query query = URL_QUERY_BUILDER.getQuery(orderProperty, offset, pageEnd);
		return executeAndCloseUrlQuery(query);
	}

	@Override
	public List<Image> getImages(String orderProperty, long offset, long pageEnd) {
		Query query = IMAGE_QUERY_BUILDER.getQuery(orderProperty, offset, pageEnd);
		return executeAndCloseImageQuery(query);
	}
	
	//===================================================
	//Helpers methods 
	//===================================================

	private void updateTables(PersistenceManager pm, Comment comment, int vote) {
		updateHost(pm, comment.getHost(), vote);
		updateUrl(pm, comment.getUrl(), vote);
		if (ResourceAnalyzer.isImage(comment.getPath())) {
			updateImage(pm, comment.getUrl(), vote);
		}
	}

	private void updateHost(PersistenceManager pm, String host, int vote) {
		Host toUpdate = getHost(pm, host);
		if (toUpdate == null) {
			pm.makePersistent(new Host(host));
		} else {
			toUpdate.setCreatedDate(new Date());
			toUpdate.addVote(vote);
		}
	}

	private void updateUrl(PersistenceManager pm, String url, int vote) {
		Url toUpdate = getUrl(pm, url);
		if (toUpdate == null) {
			pm.makePersistent(new Url(url));
		} else {
			toUpdate.setCreatedDate(new Date());
			toUpdate.addVote(vote);
		}
	}

	private void updateImage(PersistenceManager pm, String url, int vote) {
		Image toUpdate = getImage(pm, url);
		if (toUpdate == null) {
			pm.makePersistent(new Image(url));
		} else {
			toUpdate.setCreatedDate(new Date());
			toUpdate.addVote(vote);
		}
	}

	private Image getImage(PersistenceManager pm, String url) {
		try {
			return (Image) pm.getObjectById(Image.class, url);
		} catch (Exception e) {
			return null;
		}
	}

	private Host getHost(PersistenceManager pm, String host) {
		try {
			return (Host) pm.getObjectById(Host.class, host);
		} catch (Exception e) {
			return null;
		}
	}

	private Url getUrl(PersistenceManager pm, String url) {
		try {
			return (Url) pm.getObjectById(Url.class, url);
		} catch (Exception e) {
			return null;
		}
	}

	private Comment getComment(PersistenceManager pm, long id) {
		try {
			return (Comment) pm.getObjectById(Comment.class, new Long(id));
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Comment> execute(Query query, String url){
		return (List<Comment>) query.execute(url);
	}
	
	private List<Comment> executeAndClose(Query query, String url){
		List<Comment> result = execute(query, url);
		query.closeAll();
		return result;
	}

}
