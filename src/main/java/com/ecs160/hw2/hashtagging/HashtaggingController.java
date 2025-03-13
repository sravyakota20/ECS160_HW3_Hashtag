package com.ecs160.hw2.hashtagging;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.Options;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HashtaggingController {
    final OllamaAPI api = new OllamaAPI("http://localhost:11434");
    // Define the model name
    String modelName = "llama3.2";
    // Define a prompt for the model.
    String promptPrefix = "Please generate a hashtag for this social media post: ";

    @PostMapping("/hashtag")
    public ResponseEntity<HashtagResponse> hashtag(@RequestBody HashtagRequest request) {
        String content = request.getPostContent();
        System.out.println("HashTag: Received request: " + content);

        // Add calls to LLAMA model here.
        // For demo purposes, we added standard hashtag generation.
        String hashtag = "";
        hashtag = getOllamaResposne(content);
        if (hashtag.isEmpty()) {
            hashtag = generateHashtag(content);
        }
        HashtagResponse response = new HashtagResponse();
        response.setHashtag(hashtag);
        return ResponseEntity.ok(response);
    }

    String getOllamaResposne(String post) {
        Map<String, Object> opts = new HashMap<>();
        // Set a temperature parameter (for randomness in the output)
        opts.put("temperature", 0.7);
        // Set max tokens (limit the length of output)
        opts.put("max_tokens", 100);
        // Optionally set top_p if supported (nucleus sampling probability)
        opts.put("top_p", 0.9);
        Options options = new Options(opts);
        OllamaResult result = null;
        try {
            result = api.generate(modelName, promptPrefix + post, false, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Print the result.
        System.out.println("Model result: " + result.getResponse());
        return result.getResponse();
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
