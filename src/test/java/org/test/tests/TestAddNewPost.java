package org.test.tests;

import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TestAddNewPost extends LoggedInTestBase {
	@Test
	public void addsNewPostSuccessfully() {
		assertThat(app.site.isLoggedIn(), is(true));
		app.site.addNewPost("New test post!", "If you see this - it`s working");
		app.navigation.goToMyPublishedPostsPage();
		app.wait(5);
	}
}
