package org.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.concurrent.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TestAddNewPost extends LoggedInTestBase {
	@Test
	public void addsNewPostSuccessfully() {
		assertThat(isLoggedIn(), is(true));
		addNewPost("New test post!", "If you see this - it`s working");
		driver.get(config.getProperty("PublishedPostsPage.Url"));
		wait(5);
	}

	private boolean isLoggedIn() {
		driver.get(config.getProperty("MainPage.Url"));
		return !driver
			.findElements(By.xpath("//span[@class='s-header-item-post--long']"))
			.isEmpty();
	}

	private void addNewPost(String postName, String postText) {
		driver.get(config.getProperty("MainPage.Url"));
		driver.findElement(By.xpath("//span[@class='s-header-item-post--long']")).click();
		assertThat(driver.getCurrentUrl().equals("https://www.livejournal.com/post"), is(true));
		driver.findElement(By.xpath("//textarea[@class='_xt']")).sendKeys(postName);
		driver.findElement(By.xpath("//div[@class='public-DraftStyleDefault-block public-DraftStyleDefault-ltr']")).sendKeys(postText);
		driver.findElement(By.xpath("//button[@class='_11c _11e _11h _11q _11t _127']")).click();
		driver.findElement(By.xpath("//button[@class='_11c _11e _11m _11r _11s']")).click();
	}

	private static void wait(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
