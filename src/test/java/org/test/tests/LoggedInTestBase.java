package org.test.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.*;

public class LoggedInTestBase extends TestBase {

	@BeforeEach
	@Order(10)
	public void login() {
		app.navigation.goToLoginPage();

		var userName = app.config.getProperty("User.Name");
		var userPassword = app.config.getProperty("User.Password");
		app.login.login(userName, userPassword);

		new WebDriverWait(app.driver, 3).until(driver -> !app.login.isLoaded());
	}
}
