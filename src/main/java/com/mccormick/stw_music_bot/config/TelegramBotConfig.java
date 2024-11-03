package com.mccormick.stw_music_bot.config;

import com.mccormick.stw_music_bot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

	@Bean
	public TelegramBotsApi telegramBotsApi(TelegramBot telegramBot) throws TelegramApiException {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

		telegramBotsApi.registerBot(telegramBot);

		return telegramBotsApi;
	}
}
