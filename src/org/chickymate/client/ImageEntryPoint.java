package org.chickymate.client;

import org.chickymate.client.view.AbstractMainView;
import org.chickymate.client.view.grid.ImagePageGrid;

public class ImageEntryPoint extends BaseEntryPoint {

	@Override
	protected AbstractMainView getMainView() {
		return new ImagePageGrid(commentService);
	}
	
}
