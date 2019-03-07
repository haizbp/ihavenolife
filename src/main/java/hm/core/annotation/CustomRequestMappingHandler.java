package hm.core.annotation;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.condition.ConsumesRequestCondition;
import org.springframework.web.reactive.result.condition.HeadersRequestCondition;
import org.springframework.web.reactive.result.condition.ParamsRequestCondition;
import org.springframework.web.reactive.result.condition.PatternsRequestCondition;
import org.springframework.web.reactive.result.condition.ProducesRequestCondition;
import org.springframework.web.reactive.result.condition.RequestCondition;
import org.springframework.web.reactive.result.condition.RequestMethodsRequestCondition;
import org.springframework.web.reactive.result.method.RequestMappingInfo;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

public class CustomRequestMappingHandler extends RequestMappingHandlerMapping {

	private Logger logger = LoggerFactory.getLogger(CustomRequestMappingHandler.class);
	private final String prefix;

	public CustomRequestMappingHandler(String prefix) {
		this.prefix = prefix;
	}

	@Override
	protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
		RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
		if (info == null)
			return null;

		RequestCondition<?> condition = getCustomTypeCondition(handlerType);
		ApiVersion methodAnnotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
		ApiVersion typeAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
		if (methodAnnotation != null) {
			info = createApiVersionInfo(methodAnnotation, condition).combine(info);
		} else if (typeAnnotation != null) {
			info = createApiVersionInfo(typeAnnotation, condition).combine(info);
		} else {
			
			info = info.combine(pageExtension(condition));
		}

		logger.info(info.toString());

		return info;
	}

	private RequestMappingInfo createApiVersionInfo(ApiVersion annotation, RequestCondition<?> customCondition) {
		int[] values = annotation.value();
		PathPattern[] patterns = new PathPattern[values.length];
		for (int i = 0; i < values.length; i++) {
			patterns[i] = getPathPatternParser().parse(prefix + values[i]);

		}
		return new RequestMappingInfo(new PatternsRequestCondition(patterns), new RequestMethodsRequestCondition(),
				new ParamsRequestCondition(), new HeadersRequestCondition(), new ConsumesRequestCondition(),
				new ProducesRequestCondition(), customCondition);
	}
	
	private RequestMappingInfo pageExtension(RequestCondition<?> customCondition) {
		PathPattern[] patterns = new PathPattern[0];
		return new RequestMappingInfo(new PatternsRequestCondition(patterns), new RequestMethodsRequestCondition(),
				new ParamsRequestCondition(), new HeadersRequestCondition(), new ConsumesRequestCondition(),
				new ProducesRequestCondition(), customCondition);
	}
	
}