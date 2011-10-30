package org.chickymate.client.view.grid;

import java.util.List;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.HtmlConstants;
import org.chickymate.client.model.VotableDTO;
import org.chickymate.client.view.AbstractMainView;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractModelGrid extends AbstractMainView {
	
	private FlowPanel panel;
	protected CommentServiceAsync service;
	
	public AbstractModelGrid(CommentServiceAsync service) {
		this.service = service;
		panel = new FlowPanel();
		panel.setStyleName(HtmlConstants.MODEL_DTO_CONTAINER_STYLE_NAME);
		initWidget(panel);
	}

	protected void load(List<? extends VotableDTO> result) {
		clear();
		for(VotableDTO comment : result) {
			panel.add(createModelView(comment));
		}
	}
	
	private void clear() {
		panel.clear();
	}
	
	protected abstract Widget createModelView(VotableDTO model);
	
	@Override
	public abstract void init();

}
