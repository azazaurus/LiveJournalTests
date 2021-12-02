package org.test.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.*;
import org.test.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends TestBase {
	@Test
	public void loginWithValidData() {
		var user = Settings.getUser();

		app.login.login(user.name, user.password);
		new WebDriverWait(app.driver, 3).until(driver -> !app.login.isLoaded());

		assertTrue(app.login.isLoggedIn(user.name));
	}

	@Test
	public void loginWithInvalidData() {
		var user = Settings.getInvalidUser();

		app.login.login(user.name, user.password);

		assertFalse(app.login.isLoggedIn());
	}
}
