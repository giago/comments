package org.chickymate.server.dao.jdo.query;

import javax.jdo.Query;

import org.chickymate.server.model.Image;

public class ImageQueryBuilder extends AbstractQueryBuilder  {
	
	@Override
	protected Query createQueryInstance() {
		return getPM().newQuery(Image.class);
	}

}
