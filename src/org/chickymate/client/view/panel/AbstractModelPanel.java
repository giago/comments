package org.chickymate.client.view.panel;

import org.chickymate.client.CommentServiceAsync;
import org.chickymate.client.HtmlConstants;
import org.chickymate.client.model.VotableDTO;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractModelPanel extends Composite {
	
	private FlowPanel panel;
	protected CommentServiceAsync service;

	protected AbstractModelPanel(CommentServiceAsync service, VotableDTO model) {
		this.service = service;
		panel = new FlowPanel();
		panel.addStyleName(HtmlConstants.COMMENT_CONTAINER_STYLE_NAME);
		
		panel.add(getTitle(model));
		panel.add(getContent(model));
		initWidget(panel);
	}
	
	private Widget getTitle(VotableDTO model) {
		Panel title = createTitlePanel();
		title.add(getDateLabel(getDateContent(model)));
		title.add(createPositiveCommentLabel(model));
		title.add(createNegativeCommentLabel(model));
		return title;
	}
	
	protected abstract String getDateContent(VotableDTO model);
	
	protected abstract Widget getContent(VotableDTO model);
	
	protected Widget createPositiveCommentLabel(VotableDTO model) {
		Label label = createLabelWithStyle(" +" + model.getPositiveVotes(), HtmlConstants.COMMENT_POSITIVE_STYLE_NAME);
		return label;
	}

	protected Widget createNegativeCommentLabel(VotableDTO model) {
		Label label = createLabelWithStyle(" -" + model.getNegativeVotes(), HtmlConstants.COMMENT_NEGATIVE_STYLE_NAME);
		return label;
	}
	
	private Label getDateLabel(String date){
		return createLabelWithStyle(date, HtmlConstants.COMMENT_DATE_STYLE_NAME);
	}
	
	protected Panel createTitlePanel() {
		HorizontalPanel title = new HorizontalPanel();
		title.setStyleName(HtmlConstants.COMMENT_TITLE_STYLE_NAME);
		return title;
	}
	
	protected Label createLabelWithStyle(String value, String style) {
		Label label = new Label(value);
		label.setStyleName(style);
		return label;
	}
	
	protected Anchor createUrlAnchor(String url) {
		Anchor link = new Anchor(limit(url));
		link.setHref(url);
		link.setTarget("new");
		link.setStyleName(HtmlConstants.COMMENT_URL_STYLE_NAME);
		return link;
	}
	
	protected Widget createSeeCommentAnchor(String url) {
		return createCommentAnchor("see comments", url);
	}

	protected Widget createSeeRelatedCommentAnchor(String url) {
		return createCommentAnchor("see related", url);
	}

	private Widget createCommentAnchor(String title, String url) {
		Anchor link = new Anchor(title);
		link.setHref("/index.jsp?url=" + url);
		link.setStyleName(HtmlConstants.SEE_COMMENT_LINK);
		return link;
	}

	protected Anchor createUrlAnchorWithHttp(String url, String protocol) {
		Anchor link = createUrlAnchor(url);
		link.setHref(protocol + url);
		return link;
	}
	
	private String limit(String url) {
		if(url.length() < 50) {
			return url;
		}
		String limitedUrl = url.substring(0, 47);
		return limitedUrl + "...";
	}

}
