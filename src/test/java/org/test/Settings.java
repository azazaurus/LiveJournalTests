package org.test;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.dataformat.xml.*;
import com.fasterxml.jackson.dataformat.xml.annotation.*;
import org.test.model.*;

import java.io.*;
import java.util.*;

public abstract class Settings {
	public static String file = "settings.xml";

	public static String getChromeDriverPath() {
		return settings.chromeDriverPath;
	}

	public static String getBaseUrl() {
		return settings.baseUrl;
	}

	public static String getMainPageUrl() {
		return settings.mainPageUrl;
	}

	public static String getLoginPageUrl() {
		return settings.loginPageUrl;
	}

	public static String getAddNewPostPageUrl() {
		return settings.addNewPostPageUrl;
	}

	public static String getMyPublishedPostsPageUrl() {
		return settings.myPublishedPostsPageUrl;
	}

	public static UserData getUser() {
		return settings.user;
	}

	public static UserData getInvalidUser() {
		return settings.invalidUser;
	}

	private static final SettingsDto settings = getSettings();

	@JacksonXmlRootElement(localName = "settings")
	private static class SettingsDto {
		public String chromeDriverPath;

		@JacksonXmlProperty(localName = "base-url")
		public String baseUrl;

		@JacksonXmlProperty(localName = "main-page-url")
		public String mainPageUrl;

		@JacksonXmlProperty(localName = "login-page-url")
		public String loginPageUrl;

		@JacksonXmlProperty(localName = "add-new-post-page-url")
		public String addNewPostPageUrl;

		@JacksonXmlProperty(localName = "my-published-posts-page-url")
		public String myPublishedPostsPageUrl;

		public UserData user;

		public UserData invalidUser;

		@JacksonXmlProperty(localName = "user")
		public void setUser(Map<String, Object> user) {
			this.user = createUser(user);
		}

		@JacksonXmlProperty(localName = "invalid-user")
		public void setInvalidUser(Map<String, Object> invalidUser) {
			this.invalidUser = createUser(invalidUser);
		}

		@JacksonXmlProperty(localName = "chrome-driver")
		public void setChromeDriver(Map<String, Object> chromeDriver) {
			this.chromeDriverPath = (String)chromeDriver.get("path");
		}

		private UserData createUser(Map<String, Object> user) {
			var userData = new UserData();
			userData.name = (String)user.get("name");
			userData.password = (String)user.get("password");
			return userData;
		}
	}

	private static SettingsDto getSettings() {
		var xmlSerializer = new XmlMapper();
		var settingsXml = Settings.class.getClassLoader().getResourceAsStream(file);
		SettingsDto settings;
		try {
			settings = xmlSerializer.readValue(settingsXml, new TypeReference<>() { });
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return settings;
	}
}
