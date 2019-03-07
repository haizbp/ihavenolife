package hm.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import hm.core.action.Action;
import hm.core.entity.ActionEntity;
import hm.core.entity.PageEntity;
import hm.core.model.ActionModel;
import hm.core.model.PageInfo;
import hm.core.repository.PageRepository;

@Service
public class PageService {

	@Autowired
	private PageRepository pageRepository;

	public void doAction(Action action) {
		try {
			action.doAction();
		} catch (Exception e) {
			e.printStackTrace();
			action.setCode(500);
		} finally {
			action.getActionTree().put(action.getClass().toString(), action);
		}
	}

	public PageInfo fetchData(String path) throws Exception {
		PageInfo info = null;

		PageEntity entity = pageRepository.findByPath(path);
		if (entity != null) {
			info = new PageInfo();
			info.setId(entity.getId());
			info.setCreatedDate(entity.getCreatedDate());
			info.setDisable(entity.getDisable());
			info.setLastModified(entity.getLastModified());
			info.setPath(entity.getPath());
			info.setTitle(entity.getTitle());
			info.setViewPath(entity.getViewPath());

			ActionModel actionModel;
			for (ActionEntity action : entity.getActions()) {
				actionModel = new ActionModel();
				actionModel.setActionClass(action.getActionClass());
				actionModel.setCreatedDate(action.getCreatedDate());
				actionModel.setDisable(action.getDisable());
				actionModel.setId(action.getId());
				actionModel.setLastModified(action.getLastModified());
				actionModel.setRequiredParams(action.getRequiredParams());
				info.addAction(actionModel);
			}
		}

		return info;
	}

}
