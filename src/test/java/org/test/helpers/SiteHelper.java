package org.test.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.test.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SiteHelper extends HelperBase {
	public SiteHelper(ApplicationManager app) {
		super(app);
	}

	public boolean isNewPostAdded(String postTitle) {
		app.navigation.goToMyPublishedPostsPage();
		return app.driver.findElements(By.xpath("//a[@class='_10m']")).toString().equals(postTitle);
	}

	//браузер остается на главной странице
	public boolean isLoggedIn() {
		app.navigation.goToMainPage();
		return !app.driver.findElements(By.className(addNewPostButton.getAttribute("class"))).isEmpty();
	}

	public void addNewPost(String postName, String postText) {
		app.navigation.goToMainPage();
		addNewPostButton.click();
		assertThat(app.driver.getCurrentUrl().equals("https://www.livejournal.com/post"), is(true));
		postTitle.sendKeys(postName);
		postContent.sendKeys(postText);
		setAndPublishButton.click();
		publishButton.click();
	}

	@FindBy(xpath = "//button[@class='_11c _11e _11m _11r _11s']")
	private WebElement publishButton;

	@FindBy(xpath = "//button[@class='_11c _11e _11h _11q _11t _127']")
	private WebElement setAndPublishButton;

	@FindBy(xpath = "//textarea[@class='_xt']")
	private WebElement postTitle;

	@FindBy(xpath = "//div[@class='public-DraftStyleDefault-block public-DraftStyleDefault-ltr']")
	private WebElement postContent;

	@FindBy(xpath = "//span[@class='s-header-item-post--long']")
	private WebElement addNewPostButton;
}
