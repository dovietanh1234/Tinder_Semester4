package com.semester.tinder.controller;

import com.semester.tinder.dto.firebase.StatusEnum;
import com.semester.tinder.dto.request.Test.Message;
import com.semester.tinder.service.message.MessageFirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Controller
public class TestController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    // simpMessagingTemplate -> 1 phần module của spring message cung cấp methods gửu mess đến đích cụ thể
    // bao gồm việc gửu tn đến ng dùng cụ thể qua kênh socket
    // Gửi tin nhắn đến các đích đến (destinations) như /topic hoặc /queue
    //sử dụng một tiền tố đích đến người dùng được cấu hình, thường là /user

    @Autowired
    public MessageFirebaseService _messageFireBaseService;

//    @MessageMapping("/message")
//    @SendTo("/chatroom/public")
//    public Message receivePublicMessage(@Payload Message message){
//        System.out.println(message.toString());
//        return message;
//    }

    // all tin nhắn gửu đi sẽ được gửu vào đường dẫn này "@MessageMapping("/private-message")"
    // sau đó "simpMessagingTemplate.convertAndSendToUser" sẽ chuyển hoá tin nhắn về cho một đối tợng cụ thể
    //  Đờng dẫn sẽ là  /user/ReceiverId/private
//    @MessageMapping("/private-message")
//    public Message recMessage(@Payload Message message) throws ExecutionException, InterruptedException {
//        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getMatchId()) ,"/private",message);//  user/David/private

        // save message in fire base

//        System.out.println(message.toString());
//        System.out.println(message.getReceiverId());
//         // _messageFireBaseService.createSocketMessage(message);
//          return message;
//    }
// Phương thức convertAndSendToUser của simpMessagingTemplate được sử dụng để gửi tin nhắn đến một người dùng cụ thể thông qua WebSocket.

    // simpMessagingTemplate.convertAndSendToUser(userId, destination, payload);

    // khi ta gọi "convertAndSendToUser" spring sẽ xác định kết nối WebSocket của người dùng dựa
    // trên "userId" và gửu tin nhắn đến địa chỉ đã chỉ định.


    //http://localhost:8080/ws
    // ->  /app/chat/{roomId}
    // -> /topic/{roomId}
//    @MessageMapping("/chat/{roomId}")
//    @SendTo("/topic/{roomId}")
//    public Message chat(@DestinationVariable String roomId,@Payload Message message) {
//
//        // xử lý async await :
//       message.setTimeSent( new Date());
//       message.setStatus( StatusEnum.SENT );

//        CompletableFuture.runAsync( ()  -> {
//            try {
//                _messageFireBaseService.createSocketMessage(message);
//            }catch (Exception e){
//                throw new RuntimeException(e.getMessage());
//            }
//
//        } );
//        System.out.println(roomId);
//
//        System.out.println(message.toString());
//
//        return message;
//    }


//    @MessageMapping("/sent/{roomId}")
//    @SendTo("/instance/topic/123")
@MessageMapping("/message/{roomId}")
@SendTo("/chatroom/public/{roomId}")
    public Message message1(@DestinationVariable String roomId, Message message) throws ExecutionException, InterruptedException {
        System.out.println(message.toString());
        System.out.println(roomId);

      //  simpMessagingTemplate.convertAndSend("/instance/topic/" + roomId, message  );//  user/David/private

        // save message in fire base

        System.out.println("chạy thành công");
        // _messageFireBaseService.createSocketMessage(message);
        return message;
    }


}