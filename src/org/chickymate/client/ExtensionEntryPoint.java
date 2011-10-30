package org.chickymate.client;

import org.chickymate.client.view.AbstractMainView;
import org.chickymate.client.view.grid.EditableCommentPageGrid;

import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ExtensionEntryPoint extends BaseEntryPoint {
	
	@Override
	protected AbstractMainView getMainView() {
		ServiceDefTarget target = (ServiceDefTarget)commentService;
		String absolute = "http://crappycomments.appspot.com/extension/comment";
		target.setServiceEntryPoint(absolute);
		return new EditableCommentPageGrid(commentService, getUrlParameter());
	}
	
}
