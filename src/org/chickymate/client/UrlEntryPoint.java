package org.chickymate.client;

import org.chickymate.client.view.AbstractMainView;
import org.chickymate.client.view.grid.UrlPageGrid;

public class UrlEntryPoint extends BaseEntryPoint {

	@Override
	protected AbstractMainView getMainView() {
		return new UrlPageGrid(commentService);
	}
	
}
