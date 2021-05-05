package org.tain.config.ws;

import javax.websocket.server.ServerEndpointConfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/*
 * for doing on @Autowired
 */
@Configuration
public class CustomSpringConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {

	/*
	 * Spring application context
	 */
	private static volatile BeanFactory context;
	
	@Override
	public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
		return context.getBean(clazz);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		CustomSpringConfigurator.context = applicationContext;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	// WebSocket에서 HttpSession 얻기 (ref: https://nowonbun.tistory.com/621)
	public static final String Session = "Session";
	public static final String Context = "Context";
	
	// EndPointConfig에 HttpSession과 HttpContext를 넣는다. Request와 Response는 웹 요청, 응답시에만 필요한 데이터이기 때문에 필요없다.
	public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
		// HttpRequest로부터 Session을 받는다.
		HttpSession session = (HttpSession) request.getHttpSession();
		// HttpSession으로 부터 Context도 받는다.
		ServletContext context = session.getServletContext();
		config.getUserProperties().put(CustomSpringConfigurator.Session, session);
		config.getUserProperties().put(CustomSpringConfigurator.Context, context);
	}
	*/
}
