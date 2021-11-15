package org.test.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.test.*;

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
		inputUserName(userName);
		inputPassword(password);
		clickLogin();
	}

	private static final String userNameInputXPath = "//input[@id='user']";

	@FindBy(xpath = userNameInputXPath)
	private WebElement userNameInput;

	@FindBy(xpath = "//input[@id='lj_loginwidget_password']")
	private WebElement passwordInput;

	@FindBy(xpath = userNameInputXPath + "/ancestor::form/*[@name='action:login']")
	private WebElement loginButton;

	@FindBy(xpath = "//span[@class='s-header-item-post--long']")
	private WebElement addNewPostButton;
}
