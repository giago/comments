package org.chickymate.client.view.grid;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.model.CommentDTO;
import org.chickymate.client.model.ModelDTO;
import org.chickymate.client.model.PageDTO;
import org.chickymate.client.view.panel.CommentPanel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class CommentPageGrid extends AbstractPageGridView {
	
	public CommentPageGrid(CommentServiceAsync commentService) {
		super(commentService);
	}
	
	@Override
	protected AbstractPageGrid createPageGrid() {
		return new AbstractPageGrid(15) {
			@Override
			protected void loadPage(PageDTO page) {
				service.getComments(page, new AsyncCallback<PageDTO>() {
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
				CommentDTO comment = (CommentDTO)model;
				return new CommentPanel(service, comment) {
					@Override
					protected void vote(Long id, int vote) {
						service.vote(id, vote, new AsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								refresh();
							}
							
							@Override
							public void onFailure(Throwable caught) {
								throw new RuntimeException("Impossible to send vote, please retry thanks", caught.getCause());
							}
						});
					}
					@Override
					protected boolean isUserAbleToVote() {
						return true;
					}
					
					@Override
					protected boolean showUrl() {
						return false;
					}
				};
			}
		};
	}

}
