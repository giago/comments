package org.chickymate.client.view.grid;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.model.CommentDTO;
import org.chickymate.client.model.ModelDTO;
import org.chickymate.client.model.PageDTO;
import org.chickymate.client.view.AbstractMainView;
import org.chickymate.client.view.input.CommentInput;
import org.chickymate.client.view.input.UrlInput;
import org.chickymate.client.view.panel.CommentPanel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditableCommentPageGrid extends AbstractMainView implements GridDataReader {

	private CommentInput  commentInput;
	private CommentServiceAsync service;
	private String url;
	private FlowPanel panel;
	private AbstractPageGrid pageGrid;
	
	public EditableCommentPageGrid(CommentServiceAsync commentService) {
		this(commentService, false, null);
	}

	public EditableCommentPageGrid(CommentServiceAsync commentService, String url) {
		this(commentService, false, url);
	}
	
	public EditableCommentPageGrid(CommentServiceAsync commentService, boolean iframe, String imposedUrl) {
		service = commentService;
		url = imposedUrl;
		panel = new FlowPanel();
		if(iframe == false) {
			panel.add(createUrlInput(imposedUrl));
		}
		pageGrid = createCommentPageGrid();
		panel.add(pageGrid);
		commentInput = createCommentInput();
		panel.add(commentInput);
		initWidget(panel);
	}
	
	@Override
	public void init() {
		pageGrid.init();
	}
	
	@Override
	public void refresh() {
		pageGrid.refresh();
	}

	public void showComment(String url) {
		this.url = url;
		init();
	}
	
	private void addComment(String comment, String username) {
		service.addComment(url, comment, username, new AsyncCallback<Void>() {
			public void onFailure(Throwable caught) {
				throw new RuntimeException("Impossible to add comment, please retry thanks", caught.getCause());
			}
			public void onSuccess(Void result) {
				refresh();
			}
		});
	}
	
	private AbstractPageGrid createCommentPageGrid() {
		return new AbstractPageGrid() {
			@Override
			protected void loadPage(PageDTO page) {
				service.getComments(url, page, new AsyncCallback<PageDTO>() {
					public void onFailure(Throwable caught) {
						showFailureMessage("Seams that there are problems, sorry");
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
	
	private CommentInput createCommentInput() {
		return new CommentInput() {
			@Override
			protected void comment(String comment, String username) {
				addComment(comment, username);
			}
		};
	}
	
	private UrlInput createUrlInput(String imposeUrl) {
		UrlInput urlInput = new UrlInput(imposeUrl){
			@Override
			protected void onUrlChange(String newUrl) {
				url = newUrl;
				init();
			}
		};
		return urlInput;
	}

}
