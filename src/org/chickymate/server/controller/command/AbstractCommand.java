package org.chickymate.server.controller.command;

import org.chickymate.server.dao.CommentDao;
import org.chickymate.server.dao.FactoryDao;

public abstract class AbstractCommand {
	
	//21 I want to use a try to display page without using the total count
	//I get a result more then the page and then on client site I can decide
	protected static final int PAGE_SIZE = 20;
	
	protected CommentDao getCommentDao() {
		return FactoryDao.getCommentDao();
	}
}