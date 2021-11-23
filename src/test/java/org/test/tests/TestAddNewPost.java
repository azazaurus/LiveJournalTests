package org.test.tests;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.test.data.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class TestAddNewPost extends LoggedInTestBase {
	@ParameterizedTest
	@MethodSource("addsNewPostSuccessfully_testCaseSource")
	public void addsNewPostSuccessfully(Post post) {
		assertThat(app.site.isLoggedIn(), is(true));
		app.site.addNewPost(post.title, post.text);
		app.navigation.goToMyPublishedPostsPage();
		app.wait(5);
	}

	public static Stream<Arguments> addsNewPostSuccessfully_testCaseSource() {
		var jsonSerializer = new ObjectMapper();
		var postsJson = LoggedInTestBase.class.getClassLoader().getResourceAsStream("test-posts.json");
		Collection<Post> posts;
		try {
			posts = jsonSerializer.readValue(postsJson, new TypeReference<>() { });
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return posts.stream().map(Arguments::of);
	}
}
