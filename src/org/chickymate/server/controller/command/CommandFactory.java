package org.chickymate.server.controller.command;

import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

public class CommandFactory {
	
	private static final Logger logger = Logger.getLogger(CommandFactory.class.getName());
	
	public Command getCommand(String commandRequested) {	
		String commandClassName = verifyAndGetCommandClassName(commandRequested);
		try {
			Object command = Class.forName(commandClassName).newInstance();
			return (Command)command;
		} catch (Exception exception) {
			String msg = "Command " + commandClassName + " is not implemented yet";
			logger.severe(msg);
			throw new RuntimeException(msg);
		} 
	}
	
	private String verifyAndGetCommandClassName(String command) {
		if(StringUtils.isBlank(command)) {
			throw new RuntimeException("Command undefined. Impossible to procede!");
		}
		String commandClass  = StringUtils.capitalize(command) + "Command";
		return "org.chickymate.server.controller.command.json." + commandClass;
	}

}
