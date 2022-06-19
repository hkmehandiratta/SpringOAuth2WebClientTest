package com.appsdeveloperblog.ws.clients.PhotoAppWebClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PhotoAppWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppWebClientApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebClient webClient(ClientRegistrationRepository clientRegistrationRepo,
			OAuth2AuthorizedClientRepository oAuth2ClientRepo) {
		ServletOAuth2AuthorizedClientExchangeFilterFunction oAuth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
				clientRegistrationRepo, oAuth2ClientRepo);

		oAuth2.setDefaultOAuth2AuthorizedClient(true);
		return WebClient.builder().apply(oAuth2.oauth2Configuration()).build();
	}

}
