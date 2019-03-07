package hm.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hm.core.entity.PageEntity;
import hm.core.model.PageInfo;
import hm.core.repository.PageRepository;

@Service
public class PageService {

	@Autowired
	private PageRepository pageRepository;

	public PageInfo fetchData(String path) throws Exception{
		PageInfo info = null;

		PageEntity entity = pageRepository.findByPath(path);
		if(entity != null) {
			info = new PageInfo();
			info.setId(entity.getId());
			info.setCreatedDate(entity.getCreatedDate());
			info.setDisable(entity.getDisable());
			info.setLastModified(entity.getLastModified());
			info.setPath(entity.getPath());
			info.setTitle(entity.getTitle());
			info.setViewPath(entity.getViewPath());
		}
		
		return info;
	}

}
