package org.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.io.*;
import java.util.*;

public abstract class TestBase {
	protected static Properties config;
	protected static WebDriver driver;
	protected static LoginPage loginPage;

	@BeforeAll
	public static void setUp() {
		config = getConfig();
		driver = initializeWebDriver();
		loginPage = new LoginPage(driver);
	}

	@AfterAll
	public static void tearDown() {
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

	private static WebDriver initializeWebDriver() {
		System.setProperty("webdriver.chrome.driver", config.getProperty("ChromeDriver.Path"));
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get(config.getProperty("LoginPage.Url"));

		return driver;
	}
}
