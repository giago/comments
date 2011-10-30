package org.chickymate.client.view.grid;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.view.AbstractMainView;

import com.google.gwt.user.client.ui.FlowPanel;

public abstract class AbstractPageGridView extends AbstractMainView implements GridDataReader {

	protected CommentServiceAsync service;
	private FlowPanel panel;
	private AbstractPageGrid pageGrid;
	
	public AbstractPageGridView(CommentServiceAsync commentService) {
		service = commentService;
		panel = new FlowPanel();
		pageGrid = createPageGrid();
		panel.add(pageGrid);
		initWidget(panel);
	}


	@Override
	public void refresh() {
		pageGrid.refresh();
	}
	
	@Override
	public void init() {
		pageGrid.init();
	}
	
	protected abstract AbstractPageGrid createPageGrid();
	
}
