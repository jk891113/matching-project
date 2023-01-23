package com.dbzz.matchingproject.entity;

import com.dbzz.matchingproject.enums.ChatRoomStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ChatRoom extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;

    @Column(nullable = false)
    private long orderId;

    @Column(nullable = false)
    private String sellerId;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private ChatRoomStatusEnum status = ChatRoomStatusEnum.ON;

    public ChatRoom(long orderId, String sellerId, String customerId) {
        this.orderId = orderId;
        this.sellerId = sellerId;
        this.customerId = customerId;
    }
}
