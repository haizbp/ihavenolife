package hm.core.model;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;

import reactor.core.publisher.Mono;

@Controller
public class BaseController {


	public String getRequestPath(ServerHttpRequest request) {
		return request.getPath().toString();
	}
	
	public Object getParam(String key, ServerHttpRequest request) {
		return request.getQueryParams().get(key);
	}
	
	public MultiValueMap<String, String> getQueryParams(ServerHttpRequest request){
		return request.getQueryParams();
	}
	
	public Object getHeader(String key, ServerHttpRequest request) {
		return request.getHeaders().get(key);
	}
	
	public Mono<String> returnView(String name) {
		return Mono.just(name);
	}

}
