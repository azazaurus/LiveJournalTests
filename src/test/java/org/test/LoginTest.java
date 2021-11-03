package org.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LoginTest {
	private static Properties config;
	private static WebDriver driver;
	private static LoginPage loginPage;

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

	@Test
	public void loginsSuccessfully() {
		assertThat(loginPage.isLoaded(), is(true));

		var userName = config.getProperty("User.Name");
		var userPassword = config.getProperty("User.Password");
		loginPage.login(userName, userPassword);

		new WebDriverWait(driver, 3).until(driver -> !loginPage.isLoaded());
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

	private static void wait(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
