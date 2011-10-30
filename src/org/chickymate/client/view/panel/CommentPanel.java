package org.chickymate.client.view.panel;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.HtmlConstants;
import org.chickymate.client.model.CommentDTO;
import org.chickymate.client.model.VotableDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class CommentPanel extends AbstractModelPanel {
	
	public static final boolean SHOWURL_DEFUALT = true; 
	
	public CommentPanel(CommentServiceAsync service, VotableDTO model) {
		super(service, model);
	}

	@Override
	protected Widget getContent(VotableDTO model) {
		CommentDTO comment = getComment(model);
		
		FlowPanel panel = new FlowPanel();
		Label text = new Label(comment.getText());
		text.setStyleName(HtmlConstants.COMMENT_TEXT_STYLE_NAME);
		
		panel.add(text);
		if(showUrl()) {
			panel.add(createUrlAnchor(comment.getUrl()));
			panel.add(createSeeRelatedCommentAnchor(comment.getUrl()));
		}
		return panel;
	}

	@Override
	protected String getDateContent(VotableDTO model) {
		CommentDTO comment = getComment(model);
		String date = DateTimeFormat.getMediumDateTimeFormat().format(comment.getCreatedDate());
		return date + " - " +  comment.getUsername();
	}
	
	private CommentDTO getComment(VotableDTO model) {
		return (CommentDTO)model;
	}
	
	@Override
	protected Widget createPositiveCommentLabel(VotableDTO model) {
		final CommentDTO comment = getComment(model);
		Label label = createLabelWithStyle(" +" + model.getPositiveVotes(), HtmlConstants.COMMENT_POSITIVE_STYLE_NAME);
		label.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				checkUserLimitAndVote(comment.getId(), 1);
			}
		});
		return label;
	}

	@Override
	protected Widget createNegativeCommentLabel(VotableDTO model) {
		final CommentDTO comment = getComment(model);
		Label label = createLabelWithStyle(" -" + model.getNegativeVotes(), HtmlConstants.COMMENT_NEGATIVE_STYLE_NAME);
		label.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				checkUserLimitAndVote(comment.getId(), -1);
			}
		});
		return label;
	}
	
	private void checkUserLimitAndVote(Long id, int vote) {
		if(isUserAbleToVote()) {
			vote(id, vote);
		}
	}
	
	protected abstract void vote(Long id, int vote);

	protected abstract boolean isUserAbleToVote();
	
	protected abstract boolean showUrl();
	
}
