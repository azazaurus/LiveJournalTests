package org.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.*;

public class LoggedInTestBase extends TestBase{

	@BeforeAll
	@Order(10)
	public static void login() {
		var userName = config.getProperty("User.Name");
		var userPassword = config.getProperty("User.Password");
		loginPage.login(userName, userPassword);

		new WebDriverWait(driver, 3).until(driver -> !loginPage.isLoaded());
	}
}
