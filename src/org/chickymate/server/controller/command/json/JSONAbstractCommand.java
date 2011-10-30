package org.chickymate.server.controller.command.json;

import org.chickymate.server.controller.RequestWrapper;
import org.chickymate.server.controller.command.AbstractCommand;
import org.chickymate.server.controller.command.Command;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSONAbstractCommand extends AbstractCommand implements Command {

	public String process(RequestWrapper request) {
		Object obj =  execute(request);
		return convertObjctToJsonString(obj);
	}
	
	protected String convertObjctToJsonString(Object object) {
		try {
			JSONObject json = resultToJson(object);
			return json.toString();
		} catch (JSONException e) {
			throw new RuntimeException("Problem with the json conversion : " + object, e);			
		}
	}
	
	protected abstract Object execute(RequestWrapper request);

	protected abstract JSONObject resultToJson(Object object) throws JSONException;

	
	//what is I separate the concern... 
	
	protected JSONObject getSuccessMessage(String action, Object message) throws JSONException {
		JSONObject returnMessage = new JSONObject();
		returnMessage.put(action, (String)message);
		return returnMessage;
	}

	protected JSONObject getErrorMessage(String action, Object message) throws JSONException {
		JSONObject returnMessage = new JSONObject();
		returnMessage.put(action, (String)message);
		return returnMessage;
	}
	
}
