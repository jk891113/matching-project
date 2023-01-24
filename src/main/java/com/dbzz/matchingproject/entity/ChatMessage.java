package com.dbzz.matchingproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
@Getter
public class ChatMessage extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long messageId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "chat_room_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private ChatRoom chatRoom;

    @Column(nullable = false)
    private long chatRoomId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String message;

    public ChatMessage(long chatRoomId, String userId, String message) {
        this.chatRoomId = chatRoomId;
        this.userId = userId;
        this.message = message;
    }
}
