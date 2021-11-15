package org.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.test.helpers.*;
import org.test.tests.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class ApplicationManager {
	public final Properties config;
	public final WebDriver driver;

	public final NavigationHelper navigation;
	public final LoginHelper login;
	public final SiteHelper site;

	public ApplicationManager() {
		config = getConfig();
		driver = initializeWebDriver(config);

		navigation = new NavigationHelper(this, config.getProperty("BaseUrl"));
		login = new LoginHelper(this);
		site = new SiteHelper(this);
	}

	public void wait(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	public void stop() {
		driver.quit();
	}

	private static Properties getConfig() {
		try (InputStream inputStream = LoginTest.class.getClassLoader().getResourceAsStream("config.properties")) {
			Properties properties = new Properties();
			properties.load(inputStream);
			return properties;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private static WebDriver initializeWebDriver(Properties config) {
		System.setProperty("webdriver.chrome.driver", config.getProperty("ChromeDriver.Path"));
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		return driver;
	}
}
