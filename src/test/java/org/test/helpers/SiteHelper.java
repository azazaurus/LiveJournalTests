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

	@FindBy(xpath = "//span[text()='Опубликовать']")
	private WebElement publishButton;

	@FindBy(xpath = "//span[text()='Настроить и опубликовать']")
	private WebElement setAndPublishButton;

	@FindBy(xpath = "//textarea[@placeholder='Заголовок']")
	private WebElement postTitle;

	@FindBy(xpath = "//div[@class='public-DraftStyleDefault-block public-DraftStyleDefault-ltr']")
	private WebElement postContent;

	@FindBy(xpath = "//span[@class='s-header-item-post--long']")
	private WebElement addNewPostButton;
}
