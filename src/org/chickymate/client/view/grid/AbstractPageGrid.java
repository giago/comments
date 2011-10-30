package org.chickymate.client.view.grid;

import java.util.List;

import org.chickymate.client.HtmlConstants;
import org.chickymate.client.model.ModelDTO;
import org.chickymate.client.model.PageDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPageGrid extends Composite implements GridDataReader {
	
	private static final int DEFAULT_PAGE_SIZE = 5;
	private FlowPanel contentPanel;
	private FlowPanel bottomToolbar;
	private FlowPanel topToolbar;
	private FlowPanel panel;
	private Label loadingLabel;
	private PageDTO page;
	private int pageSize;
	
	public AbstractPageGrid() {
		this(DEFAULT_PAGE_SIZE);
	}

	public AbstractPageGrid(int pageSize) {
		this.pageSize = pageSize;
		panel = new FlowPanel();
		panel.setStyleName(HtmlConstants.COMMENT_CONTAINER_STYLE_NAME);
		
		topToolbar = new FlowPanel();
		topToolbar.setStyleName(HtmlConstants.COMMENT_TOP_TOOLBAR_STYLE_NAME);
		panel.add(topToolbar);
		
		contentPanel = new FlowPanel();
		contentPanel.setStyleName(HtmlConstants.COMMENT_CONTENT_STYLE_NAME_SN);
		loadingLabel = new Label("Loading ...");
		contentPanel.add(loadingLabel);
		panel.add(contentPanel);
		
		bottomToolbar = new FlowPanel();
		bottomToolbar.setStyleName(HtmlConstants.COMMENT_TOP_TOOLBAR_STYLE_NAME);
		panel.add(bottomToolbar);
		
		initWidget(panel);
	}
	
	@Override
	public void refresh() {
		page.setPage(0);
		loadPage(page);
	}

	@Override
	public void init() {
		if(page == null) {
			page = new PageDTO();
			page.setPage(0);
			page.setSize(pageSize);
		}
		loadPage(page);
	}
	
	public void showPage(PageDTO page) {
		loadingLabel.setVisible(false);
		this.page = page;
		updateTopToolbar();
		updateContent();
		updateBottomToolbar();
	}
	
	protected abstract void loadPage(PageDTO page);
	
	protected abstract Widget getPanel(ModelDTO model);
	
	protected void showFailureMessage(String message) {
		loadingLabel.setText(message);
	}
	
	private void updateTopToolbar() {
		topToolbar.clear();
		if(page!= null && !page.isOrderByDate()) {
			topToolbar.add(getOrderByLabel("recent",PageDTO.ORDER_BY_DATE));
		}
		if(page!= null && !page.isOrderByVote()) {
			topToolbar.add(getOrderByLabel("popular",PageDTO.ORDER_BY_VOTE));
		}
	}

	private void updateBottomToolbar() {
		bottomToolbar.clear();
		if(page != null && page.hasNext()) {
			bottomToolbar.add(getPageLabel("next", 1));
		}
		if(page != null && page.hasPrevious()) {
			bottomToolbar.add(getPageLabel("previous", -1));
		}
	}
	
	private void updateContent() {
		contentPanel.clear();
		List<? extends ModelDTO> models = page.getModels();
		page.setModels(null);
		if(models == null || models.isEmpty()) {
			contentPanel.add(new Label("There are no results"));
			return;
		}
		for(ModelDTO model: models) {
			contentPanel.add(getPanel(model));
		}
	}

	private Label getOrderByLabel(String title, final String orderProperty) {
		Label label = new Label(title);
		label.setStyleName(HtmlConstants.COMMENT_TOOLBAR_LABEL_BUTTON_SN);
		label.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				page.setOrderProperty(orderProperty);
				loadPage(page);
			}
		});
		return label;
	}
	
	private Label getPageLabel(String title, final int pageMove) {
		Label label = new Label(title);
		label.setStyleName(HtmlConstants.COMMENT_TOOLBAR_LABEL_BUTTON_SN);
		label.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				page.setPage(page.getPage() + pageMove);
				loadPage(page);
			}
		});
		return label;
	}
	
}
