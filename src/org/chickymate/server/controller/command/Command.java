package org.chickymate.server.controller.command;

import org.chickymate.server.controller.RequestWrapper;

public interface Command {
	
	String process(RequestWrapper request) throws Exception;

}
