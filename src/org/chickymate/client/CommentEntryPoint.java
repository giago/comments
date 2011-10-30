package org.chickymate.client;

import org.chickymate.client.view.AbstractMainView;
import org.chickymate.client.view.grid.CommentPageGrid;

public class CommentEntryPoint extends BaseEntryPoint{

	@Override
	protected AbstractMainView getMainView() {
		return new CommentPageGrid(commentService);
	}
	
}
