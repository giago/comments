package org.chickymate.client.view.panel;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.model.UrlDTO;
import org.chickymate.client.model.VotableDTO;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class UrlPanel extends AbstractModelPanel {

	public UrlPanel(CommentServiceAsync service, VotableDTO model) {
		super(service, model);
	}
	
	@Override
	protected Widget getContent(VotableDTO model) {
		UrlDTO url = (UrlDTO)model;
		FlowPanel panel = new FlowPanel();
		panel.add(createUrlAnchor(url.getUrl()));
		panel.add(createSeeCommentAnchor(url.getUrl()));
		return panel;
	}

	@Override
	protected String getDateContent(VotableDTO model) {
		return "With votes : ";
	}

}
