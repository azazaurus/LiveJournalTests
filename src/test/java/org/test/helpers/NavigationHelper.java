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
		goTo(Settings.getMainPageUrl());
	}

	public void goToLoginPage() {
		goTo(Settings.getLoginPageUrl());
	}

	public void goToAddNewPostPage() {
		goTo(Settings.getAddNewPostPageUrl());
	}

	public void goToMyPublishedPostsPage() {
		goTo(Settings.getMyPublishedPostsPageUrl());
		new WebDriverWait(app.driver, 3).until(driver -> app.post.isPostsPageLoaded());
	}

	private void goTo(String pageUrl) {
		if (pageUrl.equals(lastUrl))
			return;

		app.driver.get(baseUrl + pageUrl);
		lastUrl = pageUrl;
	}
}
