package com.sarvan.ws.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WsOutgoingMessage {
    String msg;
    String type;
}
