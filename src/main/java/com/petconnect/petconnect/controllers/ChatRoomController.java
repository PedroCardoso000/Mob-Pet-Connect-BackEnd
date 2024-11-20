package com.petconnect.petconnect.controllers;

import com.petconnect.petconnect.Entities.ChatRoom;
import com.petconnect.petconnect.dtos.CreateChatRoomRequest;
import com.petconnect.petconnect.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chat-room")
public class ChatRoomController {

    @Autowired
    ChatRoomService chatRoomService;

    @PostMapping()
    ResponseEntity<ChatRoom> createChatRoom(@Validated @RequestBody CreateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomService.createChatRoom(request);
        return  ResponseEntity.ok(chatRoom);
    }
}