package hm.core.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

public class ViewPage extends Mono<String> {
	
	@Override
	public void subscribe(CoreSubscriber<? super String> actual) {
		
	}

}
