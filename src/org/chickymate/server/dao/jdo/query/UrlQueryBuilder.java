package org.chickymate.server.dao.jdo.query;

import javax.jdo.Query;

import org.chickymate.server.model.Url;

public class UrlQueryBuilder extends AbstractQueryBuilder  {
	
	@Override
	protected Query createQueryInstance() {
		return getPM().newQuery(Url.class);
	}

}
