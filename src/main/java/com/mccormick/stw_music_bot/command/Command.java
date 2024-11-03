package com.mccormick.stw_music_bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Method;

@Component
public interface Command {

	SendMessage apply(Update update);
}
