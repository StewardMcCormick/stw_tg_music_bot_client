package com.mccormick.stw_music_bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TgUser {

	public TgUser(Long id, String firstname) {
		this.id = id;
		this.firstname = firstname;
	}

	private Long id;

	private String firstname;

	private String lastname;

	private String username;

	@Override
	public String toString() {
		return "TgUser{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}
