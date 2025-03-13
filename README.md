# Sravya Kota (03/12)
# ECS160-HW3: Hashtagging Microservice

## Overview
This project implements the **Hashtagging Microservice** for ECS160-HW3. It processes social media posts and assigns relevant hashtags using an LLM (LLAMA-3 via Ollama).

## Project Structure
This repository consists of:
- **Hashtagging Service** - Assigns relevant hashtags using LLM analysis.
- **JUnit Tests** - Validates hashtagging logic.
- **ModerationControllerTest** verifies both failure and success paths by simulating a call to the hashtagging service.
- **HashtaggingControllerTest** verifies that the Hashtagging service returns the expected hashtag for different inputs.

- LLAMA integration - I have downloaded various LLAMA related modules and installed and was able to write at test program, but I get the timeout. I cant figure out how to set the timeout, it is timing out in 5 seconds, not enuf time for model to respond.

[main] INFO io.github.ollama4j.models.request.OllamaGenerateEndpointCaller -- Asking model: {
  "model" : "example",
  "options" : {
    "top_p" : 0.9,
    "max_tokens" : 100,
    "temperature" : 0.7
  },
  "stream" : false,
  "prompt" : "Please generate a hashtag for this social media post: Hello world!",
  "raw" : true
}
java.net.http.HttpTimeoutException: request timed out
	at java.net.http/jdk.internal.net.http.HttpClientImpl.send(HttpClientImpl.java:954)
	at java.net.http/jdk.internal.net.http.HttpClientFacade.send(HttpClientFacade.java:133)
	at io.github.ollama4j.models.request.OllamaGenerateEndpointCaller.callSync(OllamaGenerateEndpointCaller.java:82)
	at io.github.ollama4j.OllamaAPI.generateSyncForOllamaRequestModel(OllamaAPI.java:1103)
	at io.github.ollama4j.OllamaAPI.generate(OllamaAPI.java:643)
	at io.github.ollama4j.OllamaAPI.generate(OllamaAPI.java:661)
	at com.ecs160.hw2.hashtagging.OllamaTest.main(OllamaTest.java:44)
Model result: null
