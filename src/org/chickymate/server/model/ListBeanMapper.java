package org.chickymate.server.model;

import java.util.ArrayList;
import java.util.List;

import org.chickymate.client.model.CommentDTO;
import org.chickymate.client.model.HostDTO;
import org.chickymate.client.model.ImageDTO;
import org.chickymate.client.model.ModelDTO;
import org.chickymate.client.model.UrlDTO;

@SuppressWarnings("unchecked")
public class ListBeanMapper <D extends ModelDTO, M extends Model> {
	
	protected final static ListBeanMapper<CommentDTO, Comment> COMMENT_LIST_MAPPER = new ListBeanMapper<CommentDTO, Comment>();
	protected final static ListBeanMapper<UrlDTO, Url> URL_LIST_MAPPER = new ListBeanMapper<UrlDTO, Url>();
	protected final static ListBeanMapper<ImageDTO, Image> IMAGE_LIST_MAPPER = new ListBeanMapper<ImageDTO, Image>();
	protected final static ListBeanMapper<HostDTO, Host> HOST_LIST_MAPPER = new ListBeanMapper<HostDTO, Host>();
	
	public static ListBeanMapper<CommentDTO, Comment> getCommentListMapper() {
		return COMMENT_LIST_MAPPER;
	}

	public static ListBeanMapper<UrlDTO, Url> getUrlListMapper() {
		return URL_LIST_MAPPER;
	}

	public static ListBeanMapper<ImageDTO, Image> getImageListMapper() {
		return IMAGE_LIST_MAPPER;
	}

	public static ListBeanMapper<HostDTO, Host> getHostListMapper() {
		return HOST_LIST_MAPPER;
	}
	
	public List<D> asDTOAndRemoveLast(List<M> models) {
		List<D> modelsDTO = new ArrayList<D>(models.size() - 1);
		M m;
		for(int i = 0; i < models.size()-1; i++) {
			m = models.get(i);
			modelsDTO.add((D)m.asDTO());
		}
		return modelsDTO;
	}
	
	public List<D> asDTO(List<M> models) {
		List<D> dtos = new ArrayList<D>(models.size());
		for(M model : models) {
			dtos.add((D)model.asDTO());
		}
		return dtos;
	}

}
