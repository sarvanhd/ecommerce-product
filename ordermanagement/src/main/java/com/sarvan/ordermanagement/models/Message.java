package com.sarvan.ordermanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Message {
    String msg;

    public Message(String msg) {
        this.msg = msg;
    }
}
