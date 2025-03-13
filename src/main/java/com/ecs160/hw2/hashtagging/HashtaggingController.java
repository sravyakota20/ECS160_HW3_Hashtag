package com.ecs160.hw2.hashtagging;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HashtaggingController {
    @PostMapping("/hashtag")
    public ResponseEntity<HashtagResponse> hashtag(@RequestBody HashtagRequest request) {
        String content = request.getPostContent();
        System.out.println("HashTag: Received request: " + content);
        // Here you would integrate with the LLAMA-3 model via Ollama4j.
        // For demo purposes, we simulate hashtag generation.
        String hashtag = generateHashtag(content);
        HashtagResponse response = new HashtagResponse();
        response.setHashtag(hashtag);
        return ResponseEntity.ok(response);
    }

    private String generateHashtag(String content) {
        // Example rule: if content mentions "security", tag as #security; otherwise, default.
        if (content.toLowerCase().contains("security")) {
            return "#security";
        } else if (content.toLowerCase().contains("vacation")) {
            return "#vacation";
        } else if (content.toLowerCase().contains("happy")) {
            return "#happy";
        }
        // sophisticated logic or LLM integration here.
        return "#bskypost";
    }

    static class HashtagRequest {
        private String postContent;
        public String getPostContent() { return postContent; }
        public void setPostContent(String postContent) { this.postContent = postContent; }
    }

    static class HashtagResponse {
        private String hashtag;
        public String getHashtag() { return hashtag; }
        public void setHashtag(String hashtag) { this.hashtag = hashtag; }
    }
}
