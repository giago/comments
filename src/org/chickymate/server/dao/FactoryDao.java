package org.chickymate.server.dao;

import org.chickymate.server.dao.jdo.CommentDaoImpl;

public class FactoryDao {
	
	private static CommentDao commentDao;
	
	public static CommentDao getCommentDao() {
		if(commentDao == null) {
			commentDao = new CommentDaoImpl();
		}
		return commentDao;
	}

}
