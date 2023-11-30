package com.hee.aproject.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Hello World!",
				description = "백엔드 수업자료!",
				version = "v1",
				contact = @Contact(
						name="복만",
						email ="bokman",
						url = "git.com"
						)
				
				
				
		)

)
@Configuration
public class RestApiConfiguration {

}
