package org.tain.client.config;

import javax.websocket.ClientEndpointConfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
//public class CustomSpringConfig {
public class CustomSpringConfig extends ClientEndpointConfig.Configurator implements ApplicationContextAware {

	private static volatile BeanFactory context;
	
	//@Override
	//public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
	//	return CustomSpringConfig.context.getBean(clazz);
	//}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CustomSpringConfig.context = applicationContext;
	}
}
