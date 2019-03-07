package hm.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

import hm.core.annotation.ApiVersion;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
@ApiVersion(1)
public class TestController {

	@GetMapping("/status")
	public Mono<String> status(WebSession session) {
		return Mono.just("Ok! It's working fine...");
	}

}
