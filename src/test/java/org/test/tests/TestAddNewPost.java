package org.test.tests;

import org.junit.jupiter.api.*;
import org.test.model.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TestAddNewPost extends LoggedInTestBase {
	@Test
	public void addsNewPost() {
		assertThat(app.login.isLoggedIn(), is(true));
		PostData original = new PostData("New test post!", "If you see this - it`s working");
		app.post.addNewPost(original.header, original.body);
		app.post.openLastCreatedPost();
		PostData newPostData = app.post.GetCreatedPostData();
		assertThat(original, is(samePropertyValuesAs(newPostData)));
	}
}
