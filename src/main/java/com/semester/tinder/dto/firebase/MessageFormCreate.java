package com.semester.tinder.dto.firebase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageFormCreate {
    private MultipartFile multipartFile;

    private String content;
    private int sender_id;
    private int receive_id;
    private int matches_id;
}
