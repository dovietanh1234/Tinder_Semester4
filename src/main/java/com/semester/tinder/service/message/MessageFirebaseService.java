package com.semester.tinder.service.message;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.semester.tinder.dto.firebase.GetMessagedto;
import com.semester.tinder.dto.firebase.Message;
import com.semester.tinder.dto.firebase.MessageFormCreate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class MessageFirebaseService {

    /*
    * Document_id -> chinh la id_matches
    * */
public String createMessage(Message message) {
try {
    Firestore dbFirestore = FirestoreClient.getFirestore();

    // ở đoạn code này có thể gay loi vi -> dx tuong chua dc tao ma ta da cap nhat lai gia tri -> gay error
    ApiFuture<WriteResult> collectionApiFutre = dbFirestore.collection("message").document(message.getDocument_id()).set(message);

//    DocumentReference docRef = dbFirestore.collection("Message").document(message.getDocument_id());
//    FieldValue increment = FieldValue.increment(1);
//    docRef.update("id", increment);

    return collectionApiFutre.get().getUpdateTime().toString();
    // Cach khac phuc: su dung Firestore transaction -> confirm for set value & update id at the same time( primitive way )
}catch (Exception e){
    return e.getMessage();
}

}

public String createSocketMessage(com.semester.tinder.dto.request.Test.Message message) throws ExecutionException, InterruptedException {

    Firestore dbFirestore = FirestoreClient.getFirestore();
    ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("TestMessage").document( UUID.randomUUID().toString() ).set(message);
    return collectionApiFuture.get().getUpdateTime().toString();
}



// lay ra cac tin nhan co id_matches = 5
public List<Message> getListM(GetMessagedto messagedto) throws ExecutionException, InterruptedException {

    Firestore dbFirestore = FirestoreClient.getFirestore();

    CollectionReference collectionReference = dbFirestore.collection("Message");

    ApiFuture<QuerySnapshot> future =collectionReference.orderBy("sent_time", Query.Direction.DESCENDING).whereEqualTo("matches_id", messagedto.getMatches_id()).limit(20).get();

    QuerySnapshot querySnapshot = future.get();

    List<Message> messages = new ArrayList<>();

    if ( !querySnapshot.isEmpty() ){
        for ( DocumentSnapshot documentSnapshot : querySnapshot.getDocuments() ){
            messages.add(documentSnapshot.toObject(Message.class));
        }
    }

        return messages;
}


public String deleteM( String document_id ){
    Firestore dbFirestore = FirestoreClient.getFirestore();
    ApiFuture<WriteResult> writeResult =dbFirestore.collection("Message").document(document_id).delete();
    return "delete success";
}


    public String uploadFile(MultipartFile multipartFile){
        try {
            InputStream is = multipartFile.getInputStream();
            String filename = multipartFile.getOriginalFilename();

            // upload to fire storage:
            StorageClient storageClient = StorageClient.getInstance();

            Blob blob = storageClient.bucket().create(filename, is, "image/jpeg");

            // lấy về đường dẫn url:
            String bucketName = blob.getBucket();
            String blobName = blob.getName();
            return String.format("https://storage.googleapis.com/%s/%s", bucketName, blobName);


        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String> uploadFiles( List<MultipartFile> multipartFiles ){
        List<String> urls = new ArrayList<>();

        for( MultipartFile multipartFile : multipartFiles ){
           String url = uploadFile(multipartFile);
           if(url != null){
               urls.add(url);
           }
        }

        return urls;

    }




}
