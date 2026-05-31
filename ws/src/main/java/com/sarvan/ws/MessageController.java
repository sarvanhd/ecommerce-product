package com.sarvan.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarvan.ws.models.WsIncomingMessage;
import com.sarvan.ws.models.WsOutgoingMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MessageController {

    private ObjectMapper mapper;

    private ChatClient chatClient;

    @Value("${mcpserver.url}")
    String mcpServerUrl;

    public MessageController(ObjectMapper mapper, ChatClient chatClient){
        this.mapper = mapper;
        this.chatClient = chatClient;
    }
    @MessageMapping("/user")
    @SendTo("/chat/messages")
    public WsOutgoingMessage chat(String message) throws Exception {
        log.info("Received msg: " + message.toString());
        WsIncomingMessage incomingMessage = mapper.readValue(message, WsIncomingMessage.class);
        String out = this.chatClient.prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, incomingMessage.getConversationId()))
                .user(message)
                .call()
                .content();

        return new WsOutgoingMessage(out, "agent");
    }

    @MessageMapping("/notifications")
    @SendTo("/notifications/alerts")
    public WsOutgoingMessage greeting(WsIncomingMessage incomingMessage) throws Exception {
        log.info("Received msg: " + incomingMessage.toString());
        //WsIncomingMessage incomingMessage = mapper.readValue(message, WsIncomingMessage.class);
        return new WsOutgoingMessage(incomingMessage.getMsg(), incomingMessage.getType());
    }

}
