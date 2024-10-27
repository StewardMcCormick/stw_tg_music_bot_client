package com.mccormick.stw_music_bot.config;

import com.mccormick.stw_music_bot.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBotConfig {

	public TelegramBotsApi telegramBotsApi(TelegramBot telegramBot) throws TelegramApiException {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

		telegramBotsApi.registerBot(telegramBot);

		return telegramBotsApi;
	}
}
