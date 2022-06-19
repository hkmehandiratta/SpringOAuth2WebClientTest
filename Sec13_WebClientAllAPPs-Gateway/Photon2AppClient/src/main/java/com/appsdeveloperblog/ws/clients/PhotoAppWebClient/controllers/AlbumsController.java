package com.appsdeveloperblog.ws.clients.PhotoAppWebClient.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.appsdeveloperblog.ws.api.albums.response.AlbumRest;

@Controller
@RequestMapping("/photon2")
public class AlbumsController {

	@Autowired
	OAuth2AuthorizedClientService oauth2ClientService;

	 @Autowired
	RestTemplate restTemplate;
	 
	 @Autowired
	 private RestOperations restOperations;

	@Autowired
	WebClient webClient;

	@GetMapping("/Welcome")
	public String status() {
		return "WelcomePage - Photon2 App";
	}


	@GetMapping("/albumsData")
	public String getAlbums2(Model model, @AuthenticationPrincipal OidcUser principal) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		JwtAuthenticationToken oauthToken = (JwtAuthenticationToken) auth;
		oauthToken.getToken().getTokenValue();

	//	OAuth2AuthorizedClient oauth2Client = oauth2ClientService
	//			.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
		String jwtAccessToken =	oauthToken.getToken().getTokenValue();


		System.out.println(" Principal - " + principal + " , accessTOken - " + jwtAccessToken);

		String url = "http://localhost:8082/albums";
	//	String url = "http://albumResourceServer/albums"
		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", "Bearer " + jwtAccessToken);

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

	//	ResponseEntity<List<AlbumRest>> rsEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
	//			new ParameterizedTypeReference<List<AlbumRest>>() {
	//			});
		
		ResponseEntity<List<AlbumRest>> rsEntity = restOperations.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<AlbumRest>>() {
				});		
		
		model.addAttribute("albums", rsEntity.getBody());

		// model.addAttribute("albums", Arrays.asList(album1, album2));

		return "albums";
	}
	
	@GetMapping("/albumsDataWebClientImpl")
	public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {

		String url = "http://localhost:8082/albums";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		JwtAuthenticationToken oauthToken = (JwtAuthenticationToken) auth;
		oauthToken.getToken().getTokenValue();
		String jwtAccessToken =	oauthToken.getToken().getTokenValue();


		System.out.println(" Principal - " + principal + " , accessTOken - " + jwtAccessToken);

		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", "Bearer " + jwtAccessToken);
		
	
		List<AlbumRest> albums = webClient.get().uri(url).header("Authorization", "Bearer " + jwtAccessToken).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>() {
				}).block();
		
		// More on Spring WebClient - > https://www.viralpatel.net/basic-authentication-spring-webclient/

		model.addAttribute("albums", albums);
		return "albums";
	}
	

}
