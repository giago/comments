package org.chickymate.client.view.grid;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.model.ImageDTO;
import org.chickymate.client.model.ModelDTO;
import org.chickymate.client.model.PageDTO;
import org.chickymate.client.view.panel.ImagePanel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class ImagePageGrid extends AbstractPageGridView {
	
	public ImagePageGrid(CommentServiceAsync commentService) {
		super(commentService);
	}

	@Override
	protected AbstractPageGrid createPageGrid() {
		return new AbstractPageGrid(15) {
			@Override
			protected void loadPage(PageDTO page) {
				service.getImages(page, new AsyncCallback<PageDTO>() {
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
				ImageDTO image = (ImageDTO)model;
				return new ImagePanel(service, image);
			}
		};
	}

}
