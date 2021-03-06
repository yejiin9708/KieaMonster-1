package org.tain.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		if (!Boolean.TRUE) registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST").maxAge(3600);
	}
}
