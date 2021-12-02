package org.test.helpers;

import org.openqa.selenium.support.ui.*;
import org.test.*;

public class NavigationHelper extends HelperBase {
	private final String baseUrl;
	private String lastUrl;

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
		new WebDriverWait(app.driver, 3).until(driver -> app.post.isPostsPageLoaded());
	}

	private void goTo(String pageUrlPropertyKey) {
		String pageUrl = app.config.getProperty(pageUrlPropertyKey);
		if (pageUrl.equals(lastUrl))
			return;

		app.driver.get(baseUrl + pageUrl);
		lastUrl = pageUrl;
	}
}
