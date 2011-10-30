package org.chickymate.client.view.grid;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.model.HostDTO;
import org.chickymate.client.model.ModelDTO;
import org.chickymate.client.model.PageDTO;
import org.chickymate.client.view.panel.HostPanel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class HostPageGrid extends AbstractPageGridView {
	
	public HostPageGrid(CommentServiceAsync commentService) {
		super(commentService);
	}
	
	@Override
	protected AbstractPageGrid createPageGrid() {
		return new AbstractPageGrid(15) {
			@Override
			protected void loadPage(PageDTO page) {
				service.getHosts(page, new AsyncCallback<PageDTO>() {
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
				HostDTO host = (HostDTO)model;
				return new HostPanel(service, host);
			}
		};
	}

}
