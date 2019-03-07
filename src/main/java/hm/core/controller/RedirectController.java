package hm.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;

import hm.core.BeanLoader;
import hm.core.action.Action;
import hm.core.model.ActionModel;
import hm.core.model.BaseController;
import hm.core.model.PageInfo;
import hm.core.model.Response;
import hm.core.service.PageService;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping
public class RedirectController extends BaseController {

	@Autowired
	private PageService pageService;
	@Autowired
	private BeanLoader beanLoader;

	@GetMapping
	public Mono<String> doHome(ServerWebExchange serverWebExchange, Model model, WebSession session) throws Exception {
		PageInfo pageInfo = pageService.fetchData("/");

		Map<String, Action> actionTree = new TreeMap<>();
		Action action;
		for (ActionModel actionModel : pageInfo.getActions()) {
			action = (Action) beanLoader.createBean(Class.forName(actionModel.getActionClass()));
			action.setModel(model);
			action.setServerWebExchange(serverWebExchange);
			action.setActionTree(actionTree);
			
			pageService.doAction(action);
		}
		
		return returnView(pageInfo.getViewPath());
	}
	
	@GetMapping("/page/**")
	public Mono<String> doRedirect(ServerWebExchange serverWebExchange, Model model, WebSession session) throws Exception {
		PageInfo pageInfo = pageService.fetchData(getRequestPath(serverWebExchange.getRequest()));

		Map<String, Action> actionTree = new TreeMap<>();
		Action action;
		for (ActionModel actionModel : pageInfo.getActions()) {
			action = (Action) beanLoader.createBean(Class.forName(actionModel.getActionClass()));
			action.setModel(model);
			action.setServerWebExchange(serverWebExchange);
			action.setActionTree(actionTree);
			
			pageService.doAction(action);
		}
		
		return returnView(pageInfo.getViewPath());
	}
	
	@GetMapping("/api/**")
	public @ResponseBody Mono<Response> doGet(ServerWebExchange serverWebExchange, Model model, WebSession session) throws Exception {
		PageInfo pageInfo = pageService.fetchData(getRequestPath(serverWebExchange.getRequest()));

		Map<String, Action> actionTree = new TreeMap<>();
		Action action = null;
		for (ActionModel actionModel : pageInfo.getActions()) {
			action = (Action) beanLoader.createBean(Class.forName(actionModel.getActionClass()));
			action.setModel(model);
			action.setServerWebExchange(serverWebExchange);
			action.setActionTree(actionTree);
			
			pageService.doAction(action);
		}
		
		return returnData(action);
	}

}
