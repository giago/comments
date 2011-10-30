package org.chickymate.client;

import org.chickymate.client.view.AbstractMainView;
import org.chickymate.client.view.grid.EditableCommentPageGrid;

public class IframeEntryPoint extends BaseEntryPoint {

	@Override
	protected AbstractMainView getMainView() {
		return new EditableCommentPageGrid(commentService, true, getUrlParameter());
	}
	
}
