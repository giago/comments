package org.chickymate.client.view.panel;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.model.HostDTO;
import org.chickymate.client.model.VotableDTO;

import com.google.gwt.user.client.ui.Widget;

public class HostPanel extends AbstractModelPanel {

	public HostPanel(CommentServiceAsync service, VotableDTO model) {
		super(service, model);
	}
	
	@Override
	protected Widget getContent(VotableDTO model) {
		HostDTO host = (HostDTO)model;
		return createUrlAnchorWithHttp(host.getHost(), "http://");
	}

	@Override
	protected String getDateContent(VotableDTO model) {
		return "With votes : ";
	}

}
