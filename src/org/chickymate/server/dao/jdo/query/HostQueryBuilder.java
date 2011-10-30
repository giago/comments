package org.chickymate.server.dao.jdo.query;

import javax.jdo.Query;

import org.chickymate.server.model.Host;

public class HostQueryBuilder extends AbstractQueryBuilder  {
	
	@Override
	protected Query createQueryInstance() {
		return getPM().newQuery(Host.class);
	}

}
