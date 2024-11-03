package com.mccormick.stw_music_bot.command;

import com.mccormick.stw_music_bot.entity.TgUser;
import com.mccormick.stw_music_bot.service.TgUserService;
import com.mccormick.stw_music_bot.util.client.RestApiTgUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class StartCommand implements Command{

	private final TgUserService tgUserService;

	@Autowired
	public StartCommand(TgUserService tgUserService) {
		this.tgUserService = tgUserService;
	}

	@Override
	public SendMessage apply(Update update) {
		SendMessage sendMessage = new SendMessage();

		tgUserService.identifyUser(update, sendMessage);
		return sendMessage;
	}
}
