package org.chickymate.client;

import org.chickymate.client.view.AbstractMainView;
import org.chickymate.client.view.grid.HostPageGrid;

public class HostEntryPoint extends BaseEntryPoint {

	@Override
	protected AbstractMainView getMainView() {
		return new HostPageGrid(commentService);
	}
	
}
