package com.codamo.codamo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("custom")
@Data
public class CustomSecurityProperties {
	boolean allowCredentials;
	List<String> allowedOrigins;
	List<String> allowedHeaders;
	List<String> exposedHeaders;
	List<String> allowedMethods;
	List<String> allowedPublicApis;
}
