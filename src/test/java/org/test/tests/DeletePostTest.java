package org.test.tests;

import org.junit.jupiter.api.*;
import org.test.model.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class DeletePostTest extends AuthBase {
	@Test
	public void deletesPost() {
		app.post.openLastCreatedPost();
		PostData postData = app.post.GetCreatedPostData();
		app.post.deletePost();
		app.post.openLastCreatedPost();
		PostData postData2 = app.post.GetCreatedPostData();
		assertThat(postData.header, is(not(postData2.header)));
	}
}
