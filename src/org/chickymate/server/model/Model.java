package org.chickymate.server.model;

import org.chickymate.client.model.ModelDTO;

public abstract class Model<D extends ModelDTO>{

	public abstract D asDTO();
	
}
