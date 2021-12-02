package org.test.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.test.*;

import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class LoginHelper extends HelperBase {
	public LoginHelper(ApplicationManager app) {
		super(app);
	}

	public boolean isLoaded() {
		return !app.driver.findElements(By.xpath(userNameInputXPath)).isEmpty();
	}

	public void inputUserName(String userName) {
		userNameInput.sendKeys(userName);
	}

	public void inputPassword(String password) {
		passwordInput.sendKeys(password);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public void login(String userName, String password) {
		if (isLoggedIn()) {
			if (isLoggedIn(userName))
				return;

			logout();
		}

		app.navigation.goToLoginPage();
		inputUserName(userName);
		inputPassword(password);
		clickLogin();
	}

	public boolean isLoggedIn() {
		app.navigation.goToMainPage();
		return app.driver.findElements(By.xpath("//*[contains(@class, 's-header-item__link--login')]")).isEmpty();
	}

	public boolean isLoggedIn(String userName) {
		if (!isLoggedIn())
			return false;

		var logoutPageUrl = logoutButton.getAttribute("href");
		var currentUserName = getUserNameFromLogoutPageUrl(logoutPageUrl);
		return currentUserName.equalsIgnoreCase(userName);
	}

	public void logout() {
		app.navigation.goToMainPage();
		app.driver.get(logoutButton.getAttribute("href"));
	}

	private static String getUserNameFromLogoutPageUrl(String logoutUrl) {
		URL parsedUrl;
		try {
			parsedUrl = new URL(logoutUrl);
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		}

		var queryStringParameters = parseQueryString(parsedUrl.getQuery());
		return queryStringParameters.stream()
			.filter(x -> x.getKey().equals("user"))
			.findFirst()
			.get().getValue();
	}

	private static List<Map.Entry<String, String>> parseQueryString(String queryString) {
		return Pattern.compile("&")
			.splitAsStream(queryString)
			.map(s -> Arrays.copyOf(s.split("=", 2), 2))
			.map(o -> Map.entry(decode(o[0]), decode(o[1])))
			.collect(Collectors.toList());
	}

	private static String decode(final String encoded) {
		return Optional.ofNullable(encoded)
			.map(e -> URLDecoder.decode(e, StandardCharsets.UTF_8))
			.orElse(null);
	}

	private static final String userNameInputXPath = "//input[@id='user']";

	@FindBy(xpath = userNameInputXPath)
	private WebElement userNameInput;

	@FindBy(xpath = "//input[@id='lj_loginwidget_password']")
	private WebElement passwordInput;

	@FindBy(xpath = userNameInputXPath + "/ancestor::form/*[@name='action:login']")
	private WebElement loginButton;

	@FindBy(xpath = "//a[contains(@class, 's-header-sub-list-item__link--logout') and text()='Выйти']")
	private WebElement logoutButton;
}
