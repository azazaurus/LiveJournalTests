package org.test.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.test.*;

public class NavigationHelper extends HelperBase {
	private final String baseUrl;

	public NavigationHelper(ApplicationManager app, String baseUrl) {
		super(app);

		this.baseUrl = baseUrl;
	}

	public void goToMainPage() {
		goTo("MainPage.Url");
	}

	public void goToLoginPage() {
		goTo("LoginPage.Url");
	}

	public void goToAddNewPostPage() {
		goTo("AddNewPostPage.Url");
	}

	public void goToMyPublishedPostsPage() {
		goTo("MyPublishedPostsPage.Url");
		new WebDriverWait(app.driver, 3)
			.until(driver -> !driver.findElements(By.xpath("(//h3[@class='_10m']/a)[1]")).isEmpty());
	}

	private void goTo(String pageUrlPropertyKey) {
		String pageUrl = app.config.getProperty(pageUrlPropertyKey);
		app.driver.get(baseUrl + pageUrl);
	}
}
