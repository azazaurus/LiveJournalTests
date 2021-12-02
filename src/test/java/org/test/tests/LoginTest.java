package org.test.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends TestBase {
	@Test
	public void loginWithValidData() {
		var userName = app.config.getProperty("User.Name");
		var userPassword = app.config.getProperty("User.Password");

		app.login.login(userName, userPassword);
		new WebDriverWait(app.driver, 3).until(driver -> !app.login.isLoaded());

		assertTrue(app.login.isLoggedIn(userName));
	}

	@Test
	public void loginWithInvalidData() {
		var userName = app.config.getProperty("InvalidUser.Name");
		var userPassword = app.config.getProperty("InvalidUser.Password");

		app.login.login(userName, userPassword);

		assertFalse(app.login.isLoggedIn());
	}
}
