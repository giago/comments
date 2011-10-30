package org.chickymate.client.view.input;

import org.chickymate.client.HtmlConstants;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;

public abstract class UrlInput extends Composite {

	private FlowPanel panel;
	private Label urlLabelInput;
	private TextArea urlTextAreaInput;
	private Button searchButton;
	
	public UrlInput(String url) {
		panel = new FlowPanel();
		panel.setStyleName(HtmlConstants.COMMENT_URL_INPUT_CONTAINER);
		Label urlLabel = new Label();
		urlLabel.setStyleName(HtmlConstants.URL_LABEL);
		panel.add(urlLabel);

		urlLabelInput = new Label(limit(url));
		urlLabelInput.setStyleName(HtmlConstants.URL_LABEL_INPUT_FIELD);
		urlLabelInput.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				urlLabelInput.setVisible(false);
				urlTextAreaInput.setVisible(true);
				searchButton.setVisible(true);
			}
		});
		panel.add(urlLabelInput);
		
		urlTextAreaInput = new TextArea();
		urlTextAreaInput.setStyleName(HtmlConstants.URL_TEXT_AREA_INPUT_FIELD);
		urlTextAreaInput.setVisible(false);
		urlTextAreaInput.setValue(url);
		panel.add(urlTextAreaInput);
		
		searchButton = new Button("Search");
		searchButton.setStyleName(HtmlConstants.COMMENT_INPUT_BUTTON);
		searchButton.setVisible(false);
		searchButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				onUrlChange(urlTextAreaInput.getValue());
			}
		});
		panel.add(searchButton);
		initWidget(panel);
	}
	
	public UrlInput() {
		this(Window.Location.getHref());
	}
	
	public String getUrl() {
		return urlTextAreaInput.getValue();
	}
	
	protected abstract void onUrlChange(String url);

	private String limit(String url) {
		if(url.length() < 70) {
			return url;
		}
		String limitedUrl = url.substring(0, 67);
		return limitedUrl + "...";
	}
}
