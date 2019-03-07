package hm.core.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class RequestFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

		ServerHttpRequest request = serverWebExchange.getRequest();
		ServerHttpResponse response = serverWebExchange.getResponse();
		
		HttpHeaders headers = request.getHeaders();
		
		return webFilterChain.filter(serverWebExchange);
	}
}