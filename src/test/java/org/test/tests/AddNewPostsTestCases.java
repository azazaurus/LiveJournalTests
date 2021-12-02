package org.test.tests;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.params.provider.*;
import org.test.model.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

@SuppressWarnings("unused")
public class AddNewPostsTestCases {
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
