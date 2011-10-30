package org.chickymate.server.controller.command.dto;

import java.util.List;

import org.chickymate.client.model.PageDTO;
import org.chickymate.server.model.Comment;
import org.chickymate.server.model.ListBeanMapper;

public class GetPageOfComment extends DTOAbstractCommand {

	public PageDTO execute(String url, PageDTO page) {
		List<Comment> result = getCommentDao().getComments(url, page.getOrderProperty(), page.getOffset(), page.getPageEnd());
		updatePageWithResultInformation(page, result);
		return page;
	}

	protected void updatePageWithResultInformation(PageDTO page, List<Comment> result) {
		if (result.size() > page.getSize()) {
			page.setNextPage(true);
			page.setModels(ListBeanMapper.getCommentListMapper().asDTOAndRemoveLast(result));
		} else {
			page.setNextPage(false);
			page.setModels(ListBeanMapper.getCommentListMapper().asDTO(result));
		}
	}
}
