# Gemini API Demo

A simple Spring Boot application that demonstrates how to integrate with Google's Gemini AI models using only an API key.

## Overview

This project provides a straightforward implementation of a web application that interacts with Google's Gemini AI models with memory between interactions. It features:

- A clean, intuitive web interface built with Thymeleaf
- A RESTful API for programmatic access to Gemini's capabilities
- Simple configuration with minimal setup requirements using Spring AI

## Maintaining Chat Context with Memory Advisors


To preserve context across interactions in the chat client, a `MessageChatMemoryAdvisor` is implemented during the `ChatClient` object's creation:
```
public GeminiService(ChatClient.Builder chatClientBuilder, RestClient.Builder restClientBuilder) {
     this.chatClient = chatClientBuilder
             .defaultOptions(ChatOptions
                     .builder()
                     .build())
             .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
             .build();
    // ...
}
```
Spring AI offers various advisors to enhance AI-driven interactions in your Spring applications. For comprehensive information about advisors, refer to the [official Spring AI documentation](https://docs.spring.io/spring-ai/reference/api/advisors.html).

### Chat Memory Advisors
The following chat memory advisors are particularly relevant:
- **MessageChatMemoryAdvisor**
  Retrieves memory and adds it as a collection of messages to the prompt, preserving the conversation's structure. Note that not all AI models support this approach.
- **PromptChatMemoryAdvisor**
  Retrieves memory and incorporates it into the prompt's system text.
- **VectorStoreChatMemoryAdvisor**
  Retrieves memory from a VectorStore and integrates it into the prompt's system text. This advisor is particularly useful for efficiently searching and retrieving relevant information from large datasets.


## Features

- **Web Interface**: A user-friendly front end similar to ChatGPT or Gemini's own interface
- **REST API**: Endpoints for submitting prompts and retrieving available models
- **Spring Boot Architecture**: Built on the robust Spring Boot framework

## Prerequisites

- Java 21
- Maven
- A Google AI Studio API key for Gemini

## Setup

1. Clone the repository:
   ```
   git clone https://github.com/gh-charlie-o/ai-gemini-api-key-example.git gemini-api
   cd gemini-api
   ```

2. Set your Gemini API key as an environment variable:
   ```
   export GEMINI_API_KEY=your_api_key_here
   ```
   
   On Windows:
   ```
   set GEMINI_API_KEY=your_api_key_here
   ```

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

5. Access the application at `http://localhost:8080`

## API Documentation

Once the application is running, you can access the OpenAPI documentation at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`

### Available Endpoints

- `POST /query` - Submit a prompt to the Gemini AI model
- `GET /models` - Get a list of available Gemini models

## Technology Stack

- **Spring Boot** - Application framework
- **Thymeleaf** - Server-side Java template engine
- **Spring AI** - AI integration framework
- **Lombok** - Java annotation library
- **SpringDoc OpenAPI** - API documentation

## Configuration

The application is configured to use the Gemini 2.0 Flash model by default. You can modify the configuration in the `application.yml` file to use different models or adjust other settings.

## Important Notes

- The API key must be set as an environment variable named `GEMINI_API_KEY`
- Never commit your API key to version control
- There may be usage limitations based on your Google AI Studio account tier

## License

MIT License

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.