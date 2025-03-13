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
