package com.mccormick.stw_music_bot;

import com.mccormick.stw_music_bot.util.CommandsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

	private final CommandsHandler commandsHandler;

	@Autowired
	public TelegramBot(@Value("${bot.token}") String botToken, CommandsHandler commandsHandler) {
		super(botToken);
		this.commandsHandler = commandsHandler;
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			Long chatId = update.getMessage().getChatId();
			String text = update.getMessage().getText();
			if (text.startsWith("/")) {
				try {
					execute(commandsHandler.handleCommand(update));
				} catch (TelegramApiException e) {
					throw new RuntimeException(e);
				}
			} else {
				try {
					sendMessage(chatId, text);
				} catch (TelegramApiException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public void sendMessage(Long charId, String message) throws TelegramApiException {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(charId);
		sendMessage.setText(message);
		execute(sendMessage);
	}

	@Override
	public String getBotUsername() {
		return "Stw Music Bot";
	}
}
