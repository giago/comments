package org.chickymate.server.dao.jdo.query;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.chickymate.server.dao.PMF;

public abstract class AbstractQueryBuilder {

	public static final int PAGE_SIZE = 25;
	public static final String POPULAR_ORDER = "votes desc";
	public static final String RECENT_ORDER = "createdDate desc";
	
	protected PersistenceManager getPM() {
		return PMF.get().getPersistenceManager();
	}
	
	public Query getQuery(String orderProperty, long offset, long pageEnd){
		Query query = createQueryInstance();
		query.setOrdering(orderProperty);
		query.setRange(offset, pageEnd);
		return query;
	}
	
	protected abstract Query createQueryInstance();
}
