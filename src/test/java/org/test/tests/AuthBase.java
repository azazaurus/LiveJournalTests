package org.test.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.*;
import org.test.*;

public class AuthBase extends TestBase {
	@BeforeEach
	public void login() {
		var user = Settings.getUser();

		app.login.login(user.name, user.password);

		new WebDriverWait(app.driver, 3).until(driver -> !app.login.isLoaded());
	}
}
