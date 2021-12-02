package org.test.tests;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.test.model.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TestAddNewPost extends AuthBase {
	@ParameterizedTest
	@MethodSource("org.test.tests.AddNewPostsTestCases#addsNewPost_testCaseSource")
	public void addsNewPost(PostData expectedPost) {
//		assertThat(app.login.isLoggedIn(), is(true));
//
//		app.post.addNewPost(expectedPost.header, expectedPost.body);
//		app.post.openLastCreatedPost();
//
//		PostData actualPost = app.post.GetCreatedPostData();
//		assertThat(actualPost, is(samePropertyValuesAs(expectedPost)));
	}

}
