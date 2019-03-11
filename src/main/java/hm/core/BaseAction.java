package hm.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;

import hm.core.action.Action;
import hm.core.model.Response;
import reactor.core.publisher.Mono;

@Controller
public abstract class BaseAction {

	public Map<String, Object> fetchRequiredParams(String params, ServerHttpRequest request) {

		Map<String, Object> paramsObject = new HashMap<>();
		String[] paramArr = params.split(";");

		for (String string : paramArr) {
			paramsObject.put(string, getParam(string, request));
		}

		return paramsObject;
	}

	public String getRequestPath(ServerHttpRequest request) {
		return request.getPath().toString();
	}

	public List<String> getParam(String key, ServerHttpRequest request) {
		return request.getQueryParams().get(key);
	}

	public MultiValueMap<String, String> getQueryParams(ServerHttpRequest request) {
		return request.getQueryParams();
	}

	public Object getHeader(String key, ServerHttpRequest request) {
		return request.getHeaders().get(key);
	}

	public Mono<String> returnView(String name) {
		return Mono.just(name);
	}
	
	public Mono<Response> returnData(Action action) {
		
		Response response = new Response();
		
		if(action == null || action.getCode() == 500) {
			response.setCode(500);
		}else {
			response.setData(action.getItem("return"));
		}
		
		return Mono.just(response);
	}

}
