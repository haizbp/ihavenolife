package hm.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanLoader implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	private AutowireCapableBeanFactory beanFactory;

	@Autowired
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		this.beanFactory = applicationContext.getAutowireCapableBeanFactory();
	}

	public ApplicationContext getContext() {
		return applicationContext;
	}
	
	public <T> T createBean(Class<T> clazz) {
		return beanFactory.createBean(clazz);
	}

	public <T> void addBean(Class<T> clazz, String name) {
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;

		BeanDefinitionBuilder b = BeanDefinitionBuilder.genericBeanDefinition(clazz);
		registry.registerBeanDefinition(name, b.getBeanDefinition());
		
		
	}

}
