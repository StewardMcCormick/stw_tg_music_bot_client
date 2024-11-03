package com.mccormick.stw_music_bot.service;

import com.mccormick.stw_music_bot.entity.TgUser;
import com.mccormick.stw_music_bot.exception.EntityNotFoundException;
import com.mccormick.stw_music_bot.util.client.RestApiTgUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class TgUserService {

	@Value("${bot.greetings_unregistered_message}")
	private String greetingToUnregisteredUser;

	private final RestApiTgUserClient restApiTgUserClient;

	@Autowired
	public TgUserService(RestApiTgUserClient restApiTgUserClient) {
		this.restApiTgUserClient = restApiTgUserClient;
	}

	public void identifyUser(Update update, SendMessage sendMessage) {
		Long chatId = update.getMessage().getChatId();
		sendMessage.setChatId(chatId);
		try {
			TgUser tgUser = restApiTgUserClient.getUserById(chatId);
			sendMessage.setText(tgUser.toString());
		} catch (EntityNotFoundException e) {
			sendMessage.setText(greetingToUnregisteredUser);
			registerUser(update);
		}
	}

	private void registerUser(Update update) {
		User from = update.getMessage().getFrom();
		TgUser tgUser = new TgUser(
				from.getId(),
				from.getFirstName(),
				from.getLastName(),
				from.getUserName()
		);

		restApiTgUserClient.saveUser(tgUser);
	}
}
