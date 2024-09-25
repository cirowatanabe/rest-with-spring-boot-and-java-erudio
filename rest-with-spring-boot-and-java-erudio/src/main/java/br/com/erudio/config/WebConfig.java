package br.com.erudio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// Via QUERY PARAM. localhost:8080/api/person/v1/2?mediaType=xml
//		configurer.favorParameter(true)
//					.parameterName("mediaType")
//					.ignoreAcceptHeader(true)
//					.useRegisteredExtensionsOnly(false)
//					.defaultContentType(MediaType.APPLICATION_JSON)
//					.mediaType("json", MediaType.APPLICATION_JSON)
//					.mediaType("xml", MediaType.APPLICATION_XML);
		
		// Via HEADER PARAM localhost:8080/api/person/v1/2
					
		configurer.favorParameter(false)
			.ignoreAcceptHeader(false)
			.useRegisteredExtensionsOnly(false)
			.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML);
		// pra fazer a requisicao em xml tem que ir em Headers e adicionar uma key "Accept" e value "application/xml"
	}

}
