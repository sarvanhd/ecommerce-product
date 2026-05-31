package com.sarvan.ws.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ChatClientConfig {

    @Bean
    ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(new InMemoryChatMemoryRepository())
                .maxMessages(10)
                .build();
    }
    @Bean
    public ChatClient chatClient(ChatMemory chatMemory, ChatModel chatModel, ToolCallbackProvider tools) {
        log.info("Creating ChatClient bean");
        for(ToolCallback toolName : tools.getToolCallbacks()){
            log.info("Registered tool: " + toolName.getToolDefinition().name());
        }
        ChatClient chatClient = ChatClient.builder(chatModel)
                .defaultSystem("Answer as an expert assistant.")
                .defaultToolCallbacks(tools)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
        return chatClient;
    }
}
