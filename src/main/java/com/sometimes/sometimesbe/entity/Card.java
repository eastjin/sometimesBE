package com.sometimes.sometimesbe.entity;


import com.sometimes.sometimesbe.dto.CardRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Card extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "IMAGE_ID", nullable = false)
    private Image image;

    @Builder
    private Card(CardRequestDto cardRequestDto, User user, Image image) {
        this.content = cardRequestDto.getContent();
        this.user = user;
        this.image = image;
    }

    public static Card of(CardRequestDto cardRequestDto, User user) {
        return Card.builder()
                .cardRequestDto(cardRequestDto)
                .user(user)
                .build();
    }

    public static Card of(CardRequestDto cardRequestDto, User user, Image image) {
        return Card.builder()
                .cardRequestDto(cardRequestDto)
                .user(user)
                .image(image)
                .build();
    }

    public void update(CardRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

}
