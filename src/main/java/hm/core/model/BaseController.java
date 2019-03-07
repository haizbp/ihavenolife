package hm.core.model;

import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class BaseController {

	public Mono<String> returnView(String name) {
		return Mono.just(name);
	}

}
