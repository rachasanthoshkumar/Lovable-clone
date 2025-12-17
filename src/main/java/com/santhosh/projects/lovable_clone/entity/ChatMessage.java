package com.santhosh.projects.lovable_clone.entity;

import com.santhosh.projects.lovable_clone.enums.MessageRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {

    Long id;
    ChatSession chatSession;
    String content;
    String toolCalls;
    Instant createdAt;
    Integer tokensUsed;
    MessageRole messageRole;

}
