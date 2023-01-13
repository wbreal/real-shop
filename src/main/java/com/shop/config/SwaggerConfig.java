package com.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${swagger.title:REAL SHOP}")
	private String title;

	@Value("${swagger.description: REAL SHOP 공부용 API}")
	private String description;

	@Value("${swagger.version: 1.0}")
	private String version;

	@Value("${swagger.terms-of-service-url: localhost:8080}")
	private String termsOfServiceUrl;

	@Value("${swagger.protocols: https}")
	private String[] protocols;

	//Default Docket to show all
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.protocols(new HashSet<>(Arrays.asList(protocols)))
			.apiInfo(metaData())
			.select()
			.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
			.paths(PathSelectors.any())
			.build();
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
			.title(title)
			.description(description)
			.version(version)
			.termsOfServiceUrl(termsOfServiceUrl)
			.build();
	}
}
