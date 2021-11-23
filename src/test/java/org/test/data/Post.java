package org.test.data;

public class Post {
	public final String title;
	public final String text;

	@SuppressWarnings("unused")
	private Post() {
		title = null;
		text = null;
	}

	public Post(String title, String text) {
		this.title = title;
		this.text = text;
	}
}
