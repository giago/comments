package org.chickymate.client;

import org.chickymate.client.view.AbstractMainView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public abstract class BaseEntryPoint implements EntryPoint {

	protected final CommentServiceAsync commentService = GWT.create(CommentService.class);
	private RootPanel rootPanel;
	
	@Override
	public void onModuleLoad() {
		getRootPanel().add(new Label("Loading ..."));
		AbstractMainView view = getMainView();
		getRootPanel().add(view);
		view.init();
		getRootPanel().remove(0);
	}
	
	protected abstract AbstractMainView getMainView();

	protected String getUrlParameter() {
		return Window.Location.getParameter("url");
	}
	
	private RootPanel getRootPanel() {
		if(rootPanel == null) {
			rootPanel = RootPanel.get(HtmlConstants.GWT_HOOK);
		}
		return rootPanel;
	}
}
