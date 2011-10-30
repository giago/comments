package org.chickymate.server.util;

import java.net.URL;
import java.util.logging.Logger;


/**
 * This class should manage all the parsing related to urls.
 * Given a URL is useful to be able to extract the domain
 * and maybe other stuff
 * 
 * @author LUIGI
 *
 */
public class UrlParser {

	private static final Logger logger = Logger.getLogger(UrlParser.class.getName());
	private String url;
	private String host;
	private String query;
	private String path;
	
	public UrlParser(String url) {
		try {
			URL properUrl = new URL(url);
			this.url = url;
			this.host = properUrl.getHost();
			this.query = properUrl.getQuery();
			this.path = properUrl.getPath();
		} catch(Exception e) {
			String msg = "Problem while parsing url : " + url;
			logger.severe(msg + e.getMessage());
			throw new RuntimeException(msg, e);
		}
	}

	public String getUrl() { return url; }
	public String getHost() { return host; }
	public String getQuery() { return query; }
	public String getPath() { return path; }

}
