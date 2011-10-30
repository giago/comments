package org.chickymate.client.view.grid;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.model.ModelDTO;
import org.chickymate.client.model.PageDTO;
import org.chickymate.client.model.UrlDTO;
import org.chickymate.client.view.panel.UrlPanel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class UrlPageGrid extends AbstractPageGridView {
	
	public UrlPageGrid(CommentServiceAsync commentService) {
		super(commentService);
	}
	
	@Override
	protected AbstractPageGrid createPageGrid() {
		return new AbstractPageGrid(15) {
			@Override
			protected void loadPage(PageDTO page) {
				service.getUrls(page, new AsyncCallback<PageDTO>() {
					public void onFailure(Throwable caught) {
						throw new RuntimeException("Impossible to get hosts", caught.getCause());
					}
					
					public void onSuccess(PageDTO page) {
						showPage(page);
					}
				});
			}
			
			@Override
			protected Widget getPanel(ModelDTO model) {
				UrlDTO url = (UrlDTO)model;
				return new UrlPanel(service, url);
			}
		};
	}

}
