package org.chickymate.server.dao.jdo.query;

import javax.jdo.Query;

import org.chickymate.server.model.Comment;

public class CommentQueryBuilder extends AbstractQueryBuilder {
	
	private static final String URL_FILTER = "url == urlParam";
	private static final String URL_FILTER_DECLARATION = "String urlParam";

	public Query getRecentQueryWithUrlFilter() {
		Query query = getRecentQuery();
		addUrlFilterToQuery(query);
		return query;
	}
	
	public Query getRecentQuery() {
		return getQuery(RECENT_ORDER);
	}

	public Query getQueryWithUrlFilter(String order, long offset, long pageEnd) {
		Query query = getQuery(order);
		addUrlFilterToQuery(query);
		query.setRange(offset, pageEnd);
		return query;
	}

	public Query getQueryWithUrlFilter() {
		Query query = createQueryInstance();
		addUrlFilterToQuery(query);
		return query;
	}

	public Query getQuery(String order) {
		Query query = createQueryInstance();
		query.setOrdering(order);
		return query;
	}
	
	private void addUrlFilterToQuery(Query query) {
		query.setFilter(URL_FILTER);
		query.declareParameters(URL_FILTER_DECLARATION);
	}
	
	@Override
	protected Query createQueryInstance() {
		return getPM().newQuery(Comment.class);
	}
}
