package hm.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;
import org.springframework.session.data.redis.ReactiveRedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
@EnableRedisWebSession
@EnableSpringWebSession
@EnableCaching
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {

	@Autowired
	private ReactiveRedisOperationsSessionRepository reactiveSessionRepository;

	@Bean
	@Primary
	public ReactiveSessionRepository reactiveSessionRepository() {
		return reactiveSessionRepository;
	}

}