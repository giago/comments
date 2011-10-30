package org.chickymate.client.view.panel;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.HtmlConstants;
import org.chickymate.client.model.ImageDTO;
import org.chickymate.client.model.VotableDTO;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ImagePanel extends AbstractModelPanel {

	public ImagePanel(CommentServiceAsync service, VotableDTO model) {
		super(service, model);
	}
	
	@Override
	protected Widget getContent(VotableDTO model) {
		ImageDTO image = (ImageDTO)model;
		return createImage(image.getImageUrl());
	}

	@Override
	protected String getDateContent(VotableDTO model) {
		return "With votes : ";
	}
	
	private Widget createImage(String imageUrl) {
		FlowPanel panel = new FlowPanel();
		FlowPanel panelImageContainer = new FlowPanel();
		panelImageContainer.setStyleName(HtmlConstants.IMAGE_CONTAINER);
		Image image = new Image();
		image.setUrl(imageUrl);
		panelImageContainer.add(image);
		panel.add(panelImageContainer);
		panel.add(createUrlAnchor(imageUrl));
		panel.add(createSeeCommentAnchor(imageUrl));
		return panel;
	}

}
