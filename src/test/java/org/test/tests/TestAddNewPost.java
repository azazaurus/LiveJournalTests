package org.test.tests;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.test.model.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TestAddNewPost extends AuthBase {
	@ParameterizedTest
	@MethodSource("addsNewPost_testCaseSource")
	public void addsNewPost(PostData expectedPost) {
		assertThat(app.login.isLoggedIn(), is(true));

		app.post.addNewPost(expectedPost.header, expectedPost.body);
		app.post.openLastCreatedPost();

		PostData actualPost = app.post.GetCreatedPostData();
		assertThat(actualPost, is(samePropertyValuesAs(expectedPost)));
	}

	public static Stream<Arguments> addsNewPost_testCaseSource() {
		var jsonSerializer = new ObjectMapper();
		var postsJson = AuthBase.class.getClassLoader().getResourceAsStream("test-posts.json");
		Collection<PostData> posts;
		try {
			posts = jsonSerializer.readValue(postsJson, new TypeReference<>() { });
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return posts.stream().map(Arguments::of);
	}
}
