package org.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.test.helpers.*;

import java.util.concurrent.*;

public class ApplicationManager implements AutoCloseable {
	private static ThreadLocal<ApplicationManager> app = new ThreadLocal<>();
	public final WebDriver driver;

	public final NavigationHelper navigation;
	public final LoginHelper login;
	public final PostHelper post;

	private ApplicationManager() {
		driver = initializeWebDriver();

		navigation = new NavigationHelper(this, Settings.getBaseUrl());
		login = new LoginHelper(this);
		post = new PostHelper(this);
	}

	public static ApplicationManager getInstance() {
		if (app.get()==null) {
			ApplicationManager newInstance = new ApplicationManager();
			app.set(newInstance);
		}
		return app.get();
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

	private static WebDriver initializeWebDriver() {
		System.setProperty("webdriver.chrome.driver", Settings.getChromeDriverPath());
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		return driver;
	}

	@Override
	public void close() throws Exception {
		try {
			driver.quit();
		} catch (Exception e) {
			//ignore
		}
	}
}
