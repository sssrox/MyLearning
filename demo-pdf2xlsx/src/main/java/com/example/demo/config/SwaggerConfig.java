package com.example.demo.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
//	List<SecurityScheme> schemeList = new ArrayList<>();
	

	@Bean
	public Docket postsApi() {
//		schemeList.add(new BasicAuth("basicAuth"));
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
//				.securitySchemes(schemeList)
				.apiInfo(apiInfo()).select().paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return or(regex("/api.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("PDF to Excel")
				.description("API reference for PDF to Excel")
				.contact("sssrox@gmail.com").license("Apache")
				.licenseUrl("sssrox@gmail.com").version("1.0").build();
	}
	/*
	 * @Bean(name = "multipartResolver") public CommonsMultipartResolver
	 * multipartResolver(){ CommonsMultipartResolver multipartResolver = new
	 * CommonsMultipartResolver(); // multipartResolver.setDefaultEncoding("UTF-8");
	 * return multipartResolver; }
	 */

}
