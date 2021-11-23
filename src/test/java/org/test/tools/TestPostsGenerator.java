package org.test.tools;

import com.fasterxml.jackson.databind.*;
import org.test.data.*;

import java.io.*;
import java.util.*;

public abstract class TestPostsGenerator {
	private static final Random random = new Random();
	private static final ObjectMapper jsonSerializer = new ObjectMapper();

	public static void main(String[] args) {
		var outputFileName = args[0];
		var postsCount = Integer.parseInt(args[1]);

		var posts = generatePosts(postsCount);

		writeToJsonFile(posts, outputFileName);
	}

	private static Collection<Post> generatePosts(int count) {
		var posts = new ArrayList<Post>(count);

		for (int i = 0; i < count; ++i) {
			posts.add(new Post(
				generateEnglishText(generateInt(7, 25)),
				generateEnglishText(generateInt(50, 500))));
		}

		return posts;
	}

	private static String generateEnglishText(int length) {
		var text = new StringBuilder(length + 1 + 12);

		while (text.length() < length) {
			boolean startWithCapital;
			if (text.length() == 0)
				startWithCapital = true;
			else {
				startWithCapital = generateBoolean(0.1);
				if (startWithCapital)
					text.append('.');
			}

			if (text.length() > 0)
				text.append(' ');

			var word = generateEnglishWord(generateInt(2, 12), startWithCapital);
			text.append(word);
		}

		return text.toString();
	}

	private static StringBuilder generateEnglishWord(int length, boolean startWithCapital) {
		var word = new StringBuilder(length);

		for (int i = 0; i < length; ++i) {
			var letter = (char)generateInt('a', 'z');
			word.append(letter);
		}
		if (word.length() > 0 && startWithCapital)
			word.setCharAt(0, Character.toUpperCase(word.charAt(0)));

		return word;
	}

	private static int generateInt(int fromInclusive, int toInclusive) {
		return fromInclusive + random.nextInt(toInclusive - fromInclusive + 1);
	}

	private static boolean generateBoolean(double trueValueProbability) {
		return random.nextDouble() <= trueValueProbability;
	}

	private static <T> void writeToJsonFile(T object, String fileName) {
		try {
			jsonSerializer.writeValue(new File(fileName), object);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
