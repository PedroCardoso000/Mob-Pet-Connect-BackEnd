package com.petconnect.petconnect.repositories;

import com.petconnect.petconnect.Entities.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
