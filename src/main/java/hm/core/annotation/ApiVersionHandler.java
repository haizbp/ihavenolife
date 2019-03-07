package hm.core.annotation;

import java.lang.reflect.Method;

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

public class ApiVersionHandler extends RequestMappingHandlerMapping {

	private final String prefix;

	public ApiVersionHandler(String prefix) {
		this.prefix = prefix;
	}

	@Override
	protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
		RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
		if (info == null)
			return null;

		Controller controllerAnnotation = AnnotationUtils.findAnnotation(handlerType, Controller.class);
		RestController restControllerAnnotation = AnnotationUtils.findAnnotation(handlerType, RestController.class);
		String root = "";

		if (controllerAnnotation != null && !StringUtils.isEmpty(controllerAnnotation.value())) {
			root = controllerAnnotation.value();
		}

		if (restControllerAnnotation != null && !StringUtils.isEmpty(restControllerAnnotation.value())) {
			root = restControllerAnnotation.value();
		}

		ApiVersion methodAnnotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
		if (methodAnnotation != null) {
			RequestCondition<?> methodCondition = getCustomMethodCondition(method);
			// Concatenate our ApiVersion with the usual request mapping
			info = createApiVersionInfo(methodAnnotation, methodCondition, root).combine(info);
		} else {
			ApiVersion typeAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
			if (typeAnnotation != null) {
				RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
				// Concatenate our ApiVersion with the usual request mapping
				info = createApiVersionInfo(typeAnnotation, typeCondition, root).combine(info);
			}
		}

		return info;
	}

	private RequestMappingInfo createApiVersionInfo(ApiVersion annotation, RequestCondition<?> customCondition,
			String root) {
		int[] values = annotation.value();
		PathPattern[] patterns = new PathPattern[values.length];
		for (int i = 0; i < values.length; i++) {
			patterns[i] = getPathPatternParser().parse(prefix + values[i] + root);

		}
		return new RequestMappingInfo(new PatternsRequestCondition(patterns), new RequestMethodsRequestCondition(),
				new ParamsRequestCondition(), new HeadersRequestCondition(), new ConsumesRequestCondition(),
				new ProducesRequestCondition(), customCondition);
	}

}