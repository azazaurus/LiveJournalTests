package org.test.helpers;

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
	}

	private void goTo(String pageUrlPropertyKey) {
		String pageUrl = app.config.getProperty(pageUrlPropertyKey);
		app.driver.get(baseUrl + pageUrl);
	}
}
