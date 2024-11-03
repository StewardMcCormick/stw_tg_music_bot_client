package com.mccormick.stw_music_bot.util.client;

import com.mccormick.stw_music_bot.entity.TgUser;
import com.mccormick.stw_music_bot.exception.EntityNotCreatedException;
import com.mccormick.stw_music_bot.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestApiTgUserClient {

	private final RestTemplate restTemplate;

	private final String api_uri;

	private final String userWasNotFoundText;

	@Autowired
	public RestApiTgUserClient(RestTemplate restTemplate, @Value("${bot.api_uri}") String apiUri) {
		this.restTemplate = restTemplate;
		this.userWasNotFoundText = "userWasNotFoundText";
		api_uri = apiUri + "tg_user";
	}

	public TgUser getUserById(Long id) {
		try {
			ResponseEntity<TgUser> response = restTemplate.getForEntity(api_uri + "/" + id, TgUser.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			throw new EntityNotFoundException(userWasNotFoundText);
		}
	}

	public void saveUser(TgUser tgUser) {
		try {
			restTemplate.postForObject(api_uri, tgUser, String.class);
		} catch (RuntimeException e) {
			throw new EntityNotCreatedException(e.getMessage());
		}
	}
}
