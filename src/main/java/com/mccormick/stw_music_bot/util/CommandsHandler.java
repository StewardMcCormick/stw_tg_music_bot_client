package com.mccormick.stw_music_bot.util;

import com.mccormick.stw_music_bot.command.Command;
import com.mccormick.stw_music_bot.command.StartCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
public class CommandsHandler {

	private final Map<String, Command> commands;

	public CommandsHandler(@Autowired StartCommand startCommand) {
		commands = Map.of(
				"/start", startCommand
		);
	}

	public SendMessage handleCommand(Update update) {
		String text = update.getMessage().getText();
		String commandText = text.split(" ")[0];
		Command command = commands.get(commandText);

		if (command != null) {
			return command.apply(update);
		} else {
			return new SendMessage(String.valueOf(update.getMessage().getChatId()),
					"Неизвестная команда.");
		}
	}
}
