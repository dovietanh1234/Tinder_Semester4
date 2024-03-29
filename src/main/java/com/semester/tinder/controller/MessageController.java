package com.semester.tinder.controller;

import com.semester.tinder.dto.firebase.GetMessagedto;
import com.semester.tinder.dto.firebase.Message;
import com.semester.tinder.dto.firebase.MessageFormCreate;
import com.semester.tinder.dto.response.ApiResponse;
import com.semester.tinder.service.message.MessageFirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
@RestController
@RequestMapping("/public")
public class MessageController {

    @Autowired
    public MessageFirebaseService _messageFireBaseService;

    @PostMapping("/create")
    public ApiResponse<String> create(@RequestBody MessageFormCreate message) throws ExecutionException, InterruptedException, IOException {

        ApiResponse<String> result = new ApiResponse<>();

        String Image = null;
        // HANDLE IMAGE:
    if( message.getMultipartFile() != null ){
        Image = _messageFireBaseService.uploadFile( message.getMultipartFile() );
    }

    Message message1 = new Message();

    message1.setContent(message.getContent());
    message1.setImage(Image);
    message1.setSender_id(message.getSender_id());
    message1.setReceive_id(message.getReceive_id());
    message1.setMatches_id(message.getMatches_id());

        result.setMessage("create success");
        result.setCode(200);
        result.setResult(_messageFireBaseService.createMessage(message1));
        return result;

    }

    @GetMapping("/get-message")
    public ApiResponse<List<Message>> getList(@RequestBody GetMessagedto messagedto) throws ExecutionException, InterruptedException {

        ApiResponse<List<Message>> result = new ApiResponse<>();

        result.setMessage("get data done");
        result.setCode(200);
        result.setResult(_messageFireBaseService.getListM(messagedto));

        return result;
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteL(@RequestParam String documentId){
        ApiResponse<String> result = new ApiResponse<>();

        result.setMessage("create success");
        result.setCode(200);
        result.setResult(_messageFireBaseService.deleteM(documentId));

        return result;
    }



}
