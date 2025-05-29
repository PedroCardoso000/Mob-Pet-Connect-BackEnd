package com.petconnect.petconnect.controllers;

import com.petconnect.petconnect.Entities.ChatRoom;
import com.petconnect.petconnect.dtos.ChatMessage;
import com.petconnect.petconnect.dtos.CreateChatRoomRequest;
import com.petconnect.petconnect.dtos.UserChatRoomsDTO;
import com.petconnect.petconnect.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat-room")
public class ChatRoomController {

    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping
    ResponseEntity<ChatRoom> createChatRoom(@Validated @RequestBody CreateChatRoomRequest request) {
        ChatRoom chatRoom = chatRoomService.createChatRoom(request);

        ChatMessage notification = new ChatMessage();
        notification.setSender(String.valueOf(request.sender()));
        notification.setReceiver(String.valueOf(request.receiver()));
        notification.setContent("Nova conversa iniciada");
        notification.setType("NOTIFICATION");

        // Envia direto para o usuário receptor
        messagingTemplate.convertAndSend("/topic/chat/" + request.receiver(), notification);


        return  ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/logged-user")
    ResponseEntity<List<UserChatRoomsDTO>> getLoggedUserChatRooms() {
        List<UserChatRoomsDTO> chatRooms = chatRoomService.getLoggedUserChatRooms();
        return ResponseEntity.ok(chatRooms);
    }
}