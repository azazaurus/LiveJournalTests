package org.test.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import org.test.*;
import org.test.model.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PostHelper extends HelperBase {
	public PostHelper(ApplicationManager app) {
		super(app);
	}

	public boolean isNewPostAdded(String postTitle) {
		app.navigation.goToMyPublishedPostsPage();
		return app.driver.findElements(By.xpath("//a[@class='_10m']")).toString().equals(postTitle);
	}

	public void addNewPost(String postName, String postText) {
		app.navigation.goToMainPage();
		addNewPostButton.click();
		assertThat(app.driver.getCurrentUrl().equals("https://www.livejournal.com/post"), is(true));
		postTitle.sendKeys(postName);
		postContent.sendKeys(postText);
		setAndPublishButton.click();
		publishButton.click();
		new WebDriverWait(app.driver, 3)
			.until(driver -> !driver.getCurrentUrl().equals("https://www.livejournal.com/post"));
	}

	public PostData GetCreatedPostData() {
		String header = app.driver.findElement(By.className("aentry-post__title-text")).getText();
		String body = app.driver.findElement(By.className("aentry-post__text--view")).getText();
		return new PostData(header, body);
	}

	public void openLastCreatedPost() {
		app.navigation.goToMyPublishedPostsPage();
		String newPostUrl = newAddedPostLink.getAttribute("href");
		app.driver.get(newPostUrl);
	}

	public void deletePost() {
		app.navigation.goToMyPublishedPostsPage();
		openPostForEditingPageButton.click();
		new WebDriverWait(app.driver, 3)
			.until(driver -> driver.findElement(By.xpath(deleteAPostButtonXPath)) != null);
		deleteAPostButton.click();
		new WebDriverWait(app.driver, 1)
			.until(driver -> driver.findElement(By.xpath(confirmDeletionButtonXPath)) != null);
		confirmDeletionButton.click();
		new WebDriverWait(app.driver, 3)
			.until(driver -> !driver.getCurrentUrl().endsWith("html"));
	}

	public boolean isPostsPageLoaded() {
		return !app.driver.findElements(By.xpath(newAddedPostLinkXPath)).isEmpty();
	}

	@FindBy(xpath = "(//span[@class='_10t'])[1]")
	private WebElement openPostForEditingPageButton;

	private final String deleteAPostButtonXPath = "//a[@class='_45 _46']";
	@FindBy(xpath = deleteAPostButtonXPath)
	private WebElement deleteAPostButton;

	private final String confirmDeletionButtonXPath = "//span[text()='Удалить']";
	@FindBy(xpath = confirmDeletionButtonXPath)
	private WebElement confirmDeletionButton;

	private final String newAddedPostLinkXPath = "//a[text()='Опубликовать пост']";
	@FindBy(xpath = newAddedPostLinkXPath)
	private WebElement newAddedPostLink;

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
