package hm.core.action;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.server.ServerWebExchange;

public abstract class Action {

	private int code = 200;

	private Map<String, Object> bag = new HashMap<>();

	private Map<String, Action> actionTree;

	private Model model;

	private ServerWebExchange serverWebExchange;

	public abstract void doAction() throws Exception;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Object getItem(String key) {
		return bag.get(key);
	}
	
	public Action getActionInTree(String key) {
		return actionTree.get(key);
	}

	public void addItem(String key, Object o) {
		bag.put(key, o);
	}

	public Map<String, Object> getBag() {
		return bag;
	}

	public void setBag(Map<String, Object> bag) {
		this.bag = bag;
	}

	public ServerWebExchange getServerWebExchange() {
		return serverWebExchange;
	}

	public void setServerWebExchange(ServerWebExchange serverWebExchange) {
		this.serverWebExchange = serverWebExchange;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Map<String, Action> getActionTree() {
		return actionTree;
	}

	public void setActionTree(Map<String, Action> actionTree) {
		this.actionTree = actionTree;
	}
	
}
