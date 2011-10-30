package org.chickymate.client.view.input;

import org.chickymate.client.HtmlConstants;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;

public abstract class CommentInput extends Composite {

	private FlowPanel panel;
	private LimitedTextArea textArea;
	private Button commentButton;
	private Label signLabel;
	private TextArea signInput;
	
	public CommentInput() {
		panel = new FlowPanel();
		panel.setStyleName(HtmlConstants.COMMENT_INPUT_CONTAINER);
		
		textArea = new LimitedTextArea(160) {
			@Override
			protected void statusChange(boolean isValid) {
				if(isValid) {
					commentButton.setEnabled(true);
				} else {
					commentButton.setEnabled(false);
				}
			}
		};
		panel.add(textArea);
		
		commentButton = new Button("Add your comment");
		commentButton.setStyleName(HtmlConstants.COMMENT_INPUT_BUTTON);
		commentButton.setEnabled(false);
		commentButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String value = textArea.getValue(); 
				String username = signInput.getValue(); 
				reset();
				comment(value, username);
			}
		});
		panel.add(commentButton);
		signLabel = new Label("sign it");
		signLabel.setStyleName(HtmlConstants.SIGN_IN_LABEL);
		signLabel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				signInput.setVisible(true);
			}
		});
		panel.add(signLabel);
		signInput = new TextArea();
		signInput.setStyleName(HtmlConstants.SIGN_IN_INPUT);
		signInput.setVisible(false);
		panel.add(signInput);
		initWidget(panel);
	}
	
	private void reset() {
		textArea.reset();
		signInput.setValue(null);
		signInput.setVisible(false);
	}
	
	protected abstract void comment(String comment, String username);

}
