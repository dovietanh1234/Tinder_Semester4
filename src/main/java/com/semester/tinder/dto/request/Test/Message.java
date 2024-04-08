package com.semester.tinder.dto.request.Test;

import com.semester.tinder.dto.firebase.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    private int senderId;
    private int receiverId;
    private int matchId;
    private Date timeSent;
    private String message;
    private StatusEnum status;
}
