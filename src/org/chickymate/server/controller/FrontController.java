package org.chickymate.server.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.chickymate.server.controller.command.CommandFactory;

public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FrontController.class.getName());
	private static final CommandFactory commandFactory = new CommandFactory();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getAndPost(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getAndPost(req, resp);
	}
	
	private void getAndPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			processWrapper(req, resp);
		} catch (Exception e) {
			logger.severe("error processing request " + e.getMessage());
			throw new ServletException(e);
		}
	}
	
	private void processWrapper(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		@SuppressWarnings("unchecked")
		RequestWrapper rw = new RequestWrapper(req.getParameterMap());
		String command = rw.getAction();
		logger.info("Processing request with command : " + command);
		String result = commandFactory.getCommand(command).process(rw);
		resp.setContentType("text/x-json");
		resp.getWriter().write(result);
	}

}
