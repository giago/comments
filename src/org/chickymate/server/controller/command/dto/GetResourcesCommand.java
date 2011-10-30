package org.chickymate.server.controller.command.dto;

import java.util.List;

import org.chickymate.client.model.PageDTO;
import org.chickymate.server.model.Comment;
import org.chickymate.server.model.Host;
import org.chickymate.server.model.Image;
import org.chickymate.server.model.ListBeanMapper;
import org.chickymate.server.model.Url;

public class GetResourcesCommand extends DTOAbstractCommand {

	public PageDTO getComments(PageDTO page) {
		List<Comment> result = getCommentDao().getComments(page.getOrderProperty(), page.getOffset(), page.getPageEnd());
		updateCommentPageWithResultInformation(page, result);
		return page;
	}
	
	public PageDTO getUrls(PageDTO page) {
		List<Url> result = getCommentDao().getUrls(page.getOrderProperty(), page.getOffset(), page.getPageEnd());
		updateUrlPageWithResultInformation(page, result);
		return page;
	}
	
	public PageDTO getImages(PageDTO page) {
		List<Image> result = getCommentDao().getImages(page.getOrderProperty(), page.getOffset(), page.getPageEnd());
		updateImagePageWithResultInformation(page, result);
		return page;
	}
	
	public PageDTO getHosts(PageDTO page) {
		List<Host> result = getCommentDao().getHosts(page.getOrderProperty(), page.getOffset(), page.getPageEnd());
		updateHostPageWithResultInformation(page, result);
		return page;
	}
	
	protected void updateHostPageWithResultInformation(PageDTO page, List<Host> result) {
		if (result.size() > page.getSize()) {
			page.setNextPage(true);
			page.setModels(ListBeanMapper.getHostListMapper().asDTOAndRemoveLast(result));
		} else {
			page.setNextPage(false);
			page.setModels(ListBeanMapper.getHostListMapper().asDTO(result));
		}
	}

	protected void updateUrlPageWithResultInformation(PageDTO page, List<Url> result) {
		if (result.size() > page.getSize()) {
			page.setNextPage(true);
			page.setModels(ListBeanMapper.getUrlListMapper().asDTOAndRemoveLast(result));
		} else {
			page.setNextPage(false);
			page.setModels(ListBeanMapper.getUrlListMapper().asDTO(result));
		}
	}

	protected void updateImagePageWithResultInformation(PageDTO page, List<Image> result) {
		if (result.size() > page.getSize()) {
			page.setNextPage(true);
			page.setModels(ListBeanMapper.getImageListMapper().asDTOAndRemoveLast(result));
		} else {
			page.setNextPage(false);
			page.setModels(ListBeanMapper.getImageListMapper().asDTO(result));
		}
	}

	protected void updateCommentPageWithResultInformation(PageDTO page, List<Comment> result) {
		if (result.size() > page.getSize()) {
			page.setNextPage(true);
			page.setModels(ListBeanMapper.getCommentListMapper().asDTOAndRemoveLast(result));
		} else {
			page.setNextPage(false);
			page.setModels(ListBeanMapper.getCommentListMapper().asDTO(result));
		}
	}
	
	

}
