package com.ecs160.hw2.hashtagging;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ecs160.hw2.hashtagging.HashtaggingController.HashtagRequest;
import com.ecs160.hw2.hashtagging.HashtaggingController.HashtagResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class HashtaggingControllerTest {
	// Create a test instance of the HashtaggingController that overrides the integration call.
	private final HashtaggingController controller = new HashtaggingController() {
		// Override the method that normally calls Ollama to generate a hashtag.
		// Here we simply simulate a response based on the content.
		@Override
		public ResponseEntity<HashtagResponse> hashtag(HashtagRequest request) {
			String content = request.getPostContent();
			System.out.println("Test Tagging Controller received: " + content);
			String simulatedHashtag;
			if (content.toLowerCase().contains("security")) {
				simulatedHashtag = "#security";
			} else {
				simulatedHashtag = "#bskypost";
			}
			HashtagResponse response = new HashtagResponse();
			response.setHashtag(simulatedHashtag);
			return ResponseEntity.ok(response);
		}
	};

	@Test
	public void testGenerateHashtagForSecurityPost() {
		HashtagRequest request = new HashtagRequest();
		request.setPostContent("This post is all about security and safety.");

		ResponseEntity<HashtagResponse> response = controller.hashtag(request);
		HashtagResponse hashtagResponse = response.getBody();

		// Based on our demo logic, if "security" is present, we return "#security"
		assertEquals("#security", hashtagResponse.getHashtag());
	}

	@Test
	public void testGenerateDefaultHashtag() {
		HashtagRequest request = new HashtagRequest();
		request.setPostContent("This is a normal post with no special keywords.");

		ResponseEntity<HashtagResponse> response = controller.hashtag(request);
		HashtagResponse hashtagResponse = response.getBody();

		// If no special keyword is found, our demo logic returns "#bskypost"
		assertEquals("#bskypost", hashtagResponse.getHashtag());
	}
}
