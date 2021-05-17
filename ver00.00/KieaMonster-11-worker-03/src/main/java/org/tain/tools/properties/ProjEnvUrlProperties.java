package org.tain.tools.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.url")
@Data
public class ProjEnvUrlProperties {

	private String name;  // default
	
	private String wsUri;
	
	private String dummy;  // null
}
