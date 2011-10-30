package org.chickymate.server.controller.command.dto;

import org.chickymate.client.model.CommentDTO;
import org.chickymate.client.model.HostDTO;
import org.chickymate.client.model.ImageDTO;
import org.chickymate.client.model.UrlDTO;
import org.chickymate.server.controller.command.AbstractCommand;
import org.chickymate.server.model.Comment;
import org.chickymate.server.model.Host;
import org.chickymate.server.model.Image;
import org.chickymate.server.model.ListBeanMapper;
import org.chickymate.server.model.Url;

public abstract class DTOAbstractCommand extends AbstractCommand {

	protected final static ListBeanMapper<CommentDTO, Comment> COMMENT_LIST_MAPPER = new ListBeanMapper<CommentDTO, Comment>();
	protected final static ListBeanMapper<UrlDTO, Url> URL_LIST_MAPPER = new ListBeanMapper<UrlDTO, Url>();
	protected final static ListBeanMapper<ImageDTO, Image> IMAGE_LIST_MAPPER = new ListBeanMapper<ImageDTO, Image>();
	protected final static ListBeanMapper<HostDTO, Host> HOST_LIST_MAPPER = new ListBeanMapper<HostDTO, Host>();
	
}
