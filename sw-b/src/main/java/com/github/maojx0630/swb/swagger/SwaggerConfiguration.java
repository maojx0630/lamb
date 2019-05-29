package com.github.maojx0630.swb.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-29 10:34
 * @description:
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Value("${server.port:8080}")
	private String port;

	@Value("${server.servlet.context-path:/}")
	private String path;


	@Bean
	public Docket getDocket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.github.maojx0630.swb"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("springboot Swagger 测试")
				.description("Springboot 整合Swagger2")
				.termsOfServiceUrl("localhost:"+port+path)
				.contact(new Contact("Swagger测试","localhost:"+port+path+"/swagger-ui.html","908156949@qq.com"))
				.version("1.0")
				.build();
	}

}
