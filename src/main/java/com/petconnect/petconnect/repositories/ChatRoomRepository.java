package com.petconnect.petconnect.repositories;

import com.petconnect.petconnect.Entities.ChatRoom;
import com.petconnect.petconnect.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findAllBySender(User sender);
    List<ChatRoom> findAllByReceiver(User receiver);
}
