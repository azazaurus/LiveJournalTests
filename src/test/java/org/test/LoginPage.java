package org.test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public boolean isLoaded() {
		return !driver.findElements(By.xpath(userNameInputXPath)).isEmpty();
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

	@FindBy(xpath = "//input[@id='user']/ancestor::form/*[@name='action:login']")
	private WebElement loginButton;
}
