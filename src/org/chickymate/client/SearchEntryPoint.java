package org.chickymate.client;

import org.chickymate.client.view.AbstractMainView;
import org.chickymate.client.view.grid.EditableCommentPageGrid;

import com.google.gwt.user.client.Window;

public class SearchEntryPoint extends BaseEntryPoint {

	@Override
	protected AbstractMainView getMainView() {
		String url = getUrlParameter();
		if(url == null || url.length() == 0) {
			url = Window.Location.getHref();
		}
		return new EditableCommentPageGrid(commentService, url);
	}
	
}
