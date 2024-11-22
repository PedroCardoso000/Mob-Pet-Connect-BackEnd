package com.petconnect.petconnect.services;

import com.petconnect.petconnect.Entities.ChatRoom;
import com.petconnect.petconnect.Entities.User;
import com.petconnect.petconnect.Exceptions.UserNotFoundException;
import com.petconnect.petconnect.dtos.CreateChatRoomRequest;
import com.petconnect.petconnect.dtos.UserChatRoomsDTO;
import com.petconnect.petconnect.repositories.ChatRoomRepository;
import com.petconnect.petconnect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ChatRoomService {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserRepository userRepository;

    public ChatRoom createChatRoom(CreateChatRoomRequest request) {

        User userReceiver = userRepository
                .findById(request.receiver())
                .orElseThrow(UserNotFoundException::new);

        User userSender = userRepository
                .findById(request.sender())
                .orElseThrow(UserNotFoundException::new);

        ChatRoom chatRoom = new ChatRoom(userSender, userReceiver);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    public List<UserChatRoomsDTO> getLoggedUserChatRooms() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<ChatRoom> chatRoomsAsSender = chatRoomRepository.findAllBySender(loggedUser);
        List<UserChatRoomsDTO> asSenderRetrievers = chatRoomsAsSender
                .stream()
                .map(chatRoom -> new UserChatRoomsDTO(chatRoom.getReceiver())).toList();

        List<ChatRoom> chatRoomsAsReceiver = chatRoomRepository.findAllByReceiver(loggedUser);
        List<UserChatRoomsDTO> asRetrieverRetrievers = chatRoomsAsReceiver
                .stream()
                .map(chatRoom -> new UserChatRoomsDTO(chatRoom.getSender())).toList();

        return Stream.concat(
                asSenderRetrievers.stream(),
                asRetrieverRetrievers.stream()
        ).toList();

    }
}
