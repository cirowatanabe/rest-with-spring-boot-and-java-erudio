package br.com.erudio.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.erudio.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}

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
			.mediaType("xml", MediaType.APPLICATION_XML)
			.mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);
		// pra fazer a requisicao em xml tem que ir em Headers e adicionar uma key "Accept" e value "application/xml"
		// pra fazer a requisicao em yaml no body tem que :
			// - ir em Headers e adicionar uma key "Content-Type" e value "application/x-yaml"
			// - o body tem que ser raw e text
	}

}
