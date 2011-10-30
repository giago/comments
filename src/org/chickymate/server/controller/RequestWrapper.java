package org.chickymate.server.controller;

import java.util.Map;

public class RequestWrapper {

	private static final String URL_PARAMETER = "url";
	private static final String COMMENT_PARAMETER = "comment";
	private static final String ACTION_PARAMETER = "action";
	private static final String ID_PARAMETER = "id";
	private static final String OFFSET_PARAMETER = "offset";
	private static final String VOTE_PARAMETER = "vote";
	private Map<String, Object> parameters;
	
	public RequestWrapper(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	
	public String getUrl() {
		return getParameterAsString(URL_PARAMETER);
	}

	public String getComment() {
		return getParameterAsString(COMMENT_PARAMETER);
	}

	public String getAction() {
		return getParameterAsString(ACTION_PARAMETER);
	}
	
	public String getId(){
		return getParameterAsString(ID_PARAMETER);
	}
	
	public int getOffset() {
		return getParameterAsInt(OFFSET_PARAMETER);
	}

	public int getVote() {
		return getParameterAsInt(VOTE_PARAMETER);
	}
	
	private String getParameterAsString(String parameter) {
		String[] values = (String[])parameters.get(parameter);
		if(values == null) {
			return null;
		}
		String value = values[0];
		return value;
	}

	private int getParameterAsInt(String parameter) {
		String value = getParameterAsString(parameter);
		if(value == null) {
			return 0;
		}
		return Integer.valueOf(value).intValue();
	}
}