package com.samangkar.Samangkar.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RemoveCardRequest {

    private Long userId;
    private Long cardId;

    public RemoveCardRequest(Long userId, Long cardId) {
        this.userId = userId;
        this.cardId = cardId;
    }
}