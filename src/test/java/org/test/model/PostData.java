package org.test.model;

public class PostData {
	public String header;
	public String body;

	@SuppressWarnings("unused")
	private PostData() { }

	public PostData(String header, String body) {
		this.header = header;
		this.body = body;
	}
}
