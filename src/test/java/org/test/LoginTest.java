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

public class LoginTest extends TestBase {
	@Test
	public void loginsSuccessfully() {
		assertThat(loginPage.isLoaded(), is(true));

		var userName = config.getProperty("User.Name");
		var userPassword = config.getProperty("User.Password");
		loginPage.login(userName, userPassword);

		new WebDriverWait(driver, 3).until(driver -> !loginPage.isLoaded());
	}
}
