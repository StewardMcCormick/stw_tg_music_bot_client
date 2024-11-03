package com.mccormick.stw_music_bot;

import com.mccormick.stw_music_bot.entity.TgUser;
import com.mccormick.stw_music_bot.util.client.RestApiTgUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StwMusicBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(StwMusicBotApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
