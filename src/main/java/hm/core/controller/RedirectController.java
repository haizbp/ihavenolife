package hm.core.controller;

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

import hm.core.model.BaseController;
import hm.core.model.PageInfo;
import hm.core.service.PageService;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping
public class RedirectController extends BaseController {

	@Autowired
	private PageService pageService;
	
	@GetMapping
	public Mono<String> doGet(ServerWebExchange serverWebExchange, Model model, WebSession session) throws Exception {
		ServerHttpRequest request = serverWebExchange.getRequest();
		ServerHttpResponse response = serverWebExchange.getResponse();
		
		PageInfo pageInfo = pageService.fetchData(getRequestPath(request));
		model.addAttribute("pageInfo", pageInfo);
		
		return returnView(pageInfo.getViewPath());
	}

}
