package org.test.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LoginTest extends TestBase {
	@Test
	public void loginsSuccessfully() {
		if (app.login.isLoggedIn())
			return;

		app.navigation.goToLoginPage();
		assertThat(app.login.isLoaded(), is(true));

		var userName = app.config.getProperty("User.Name");
		var userPassword = app.config.getProperty("User.Password");
		app.login.login(userName, userPassword);

		new WebDriverWait(app.driver, 3).until(driver -> !app.login.isLoaded());
		app.wait(2);
	}
}
