package com.semester.tinder.controller;

import com.semester.tinder.dto.firebase.Message;
import com.semester.tinder.dto.firebase.MessageFormCreate;
import com.semester.tinder.dto.response.ApiResponse;
import com.semester.tinder.service.message.MessageFirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/public/explore")
public class ExploreController {

    @Autowired
    public MessageFirebaseService _messageFireBaseService;
    // get về dữ liệu:
    @GetMapping
        public ResponseEntity<String> getExplore(){
        return ResponseEntity.ok("con meo đi chơi");
    }

    @PostMapping
        public ApiResponse<String> postD(@ModelAttribute MessageFormCreate message) {

       ApiResponse<String> result = new ApiResponse<>();

        String Image = "";
        // HANDLE IMAGE:
        if( message.getMultipartFile() != null ){
            Image = _messageFireBaseService.uploadFile( message.getMultipartFile() );
            if(Image == null){

                result.setMessage("error handle image");
                result.setCode(400);

                return result;
            }

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

}
