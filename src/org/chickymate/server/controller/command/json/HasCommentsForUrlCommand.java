package org.chickymate.server.controller.command.json;

import org.chickymate.server.controller.RequestWrapper;
import org.json.JSONException;
import org.json.JSONObject;

public class HasCommentsForUrlCommand extends JSONAbstractCommand {

	@Override
	protected Object execute(RequestWrapper request){
		String url = request.getUrl();
		if(url == null) {
			return Boolean.FALSE;
		}
		return getCommentDao().hasCommentsForUrl(url); 
	}

	@Override
	protected JSONObject resultToJson(Object object) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("hasComment", (Boolean)object);
		return jsonObject;
	}
	
}
