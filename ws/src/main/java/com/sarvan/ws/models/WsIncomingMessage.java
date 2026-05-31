package com.sarvan.ws.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WsIncomingMessage {
    String msg;
    String type;
    String conversationId;
}
