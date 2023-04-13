package com.aaron.iluslinn.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerUiWebMvcConfigurer implements WebMvcConfigurer {
	 

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String baseUrl = StringUtils.trimTrailingCharacter("http://localhost:8080", '/');
		registry.addResourceHandler(baseUrl + "/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
				.resourceChain(false);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		String baseUrl = StringUtils.trimTrailingCharacter("http://localhost:8080", '/');
		registry.addViewController(baseUrl + "/swagger-ui/")
				.setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
	}
	

}
