package hm.core.model;

import java.util.HashSet;
import java.util.Set;

import hm.core.entity.ActionEntity;

public class PageInfo extends AbstractModel {

	private String path;
	private String viewPath;
	private String title;
	private Set<ActionModel> actions = new HashSet<>();

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<ActionModel> getActions() {
		return actions;
	}

	public void setActions(Set<ActionModel> actions) {
		this.actions = actions;
	}
	
	public void addAction(ActionModel actionModel) {
		actions.add(actionModel);
	}

}
