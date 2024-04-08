package com.semester.tinder.controller.properties;


import com.semester.tinder.dto.firebase.ImageDTO;
import com.semester.tinder.dto.request.Profile.ProfileRequest;
import com.semester.tinder.dto.request.Profile.ProfileUpdate;
import com.semester.tinder.dto.response.ApiResponse;
import com.semester.tinder.entity.Images;
import com.semester.tinder.entity.Profile;
import com.semester.tinder.entity.User;
import com.semester.tinder.repository.IImageRepo;
import com.semester.tinder.repository.IProfileRepo;
import com.semester.tinder.repository.IUserRepo;
import com.semester.tinder.service.message.MessageFirebaseService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/profile") // can token
public class ProfileController {

    @Autowired
    private IProfileRepo _iprofileRepo;

    @Autowired
    private IUserRepo _iuserRepo;

    @Autowired
    public MessageFirebaseService _messageFireBaseService;

    @Autowired
    public IImageRepo _imageRepo;

    @GetMapping("/getId")
    public ResponseEntity<ApiResponse<Profile>> getId(@RequestParam int profileId){

        ApiResponse<Profile> result = new ApiResponse<>();

        Optional<Profile> p = _iprofileRepo.findById(profileId);

        if( p.isEmpty() ){
            result.setMessage("error! data not found");
            result.setCode(404);
            return ResponseEntity.ok(result);
        }

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult( p.get() );
        return ResponseEntity.ok(result);

    }



    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteP(@RequestParam int profileId){

        ApiResponse<String> result = new ApiResponse<>();

        Optional<Profile> p = _iprofileRepo.findById(profileId);

        if( p.isEmpty() ){
            result.setMessage("error! data not found");
            result.setCode(404);
            return ResponseEntity.ok(result);
        }

        _iprofileRepo.delete(p.get());

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult("delete profile id: " + profileId + " successfully");
        return ResponseEntity.ok(result);

    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Profile>> createNew(@RequestBody ProfileRequest profileRequest){

      Optional<User> u =  _iuserRepo.findById(profileRequest.getU_id());

        Profile p = new Profile();

        p.setBio(profileRequest.getBio() );
        p.setHeight(profileRequest.getHeight());
        p.setUser(u.orElse(null));
        p.setLanguages(profileRequest.getLanguages());
        p.setInterests(profileRequest.getInterests());
        p.setRelationship_goals(profileRequest.getRelationship_goals());
        p.setAge(profileRequest.getAge());
        p.setDate_birth( profileRequest.getDate_birth() );
        p.setPassions(profileRequest.getPassions() );
        p.setAbout_me(profileRequest.getAbout_me() );
        /*
        *     private int age;

    private Date date_birth;
        * */

        _iprofileRepo.save(p);

        ApiResponse<Profile> result = new ApiResponse<>();

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult( p );
        return ResponseEntity.ok(result);

    }



    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Profile>> update(@RequestBody ProfileUpdate profileUpdate){


        // tim id ->
        Optional<Profile> u_p = _iprofileRepo.findById(profileUpdate.getId());
        ApiResponse<Profile> result = new ApiResponse<>();

        if( u_p.isEmpty() ){
            result.setMessage("ERROR! NOT FOUND");
            result.setCode(404);
            return ResponseEntity.ok(result);
        }

        if (profileUpdate.getBio() != null) u_p.get().setBio(profileUpdate.getBio());
        if (profileUpdate.getHeight() != null) u_p.get().setHeight(profileUpdate.getHeight());
        if (profileUpdate.getLanguages() != null) u_p.get().setLanguages(profileUpdate.getLanguages());
        if (profileUpdate.getInterests() != null) u_p.get().setInterests(profileUpdate.getInterests());
        if (profileUpdate.getRelationship_goals() != null) u_p.get().setRelationship_goals(profileUpdate.getRelationship_goals());
        if( profileUpdate.getAge() > 0 ) u_p.get().setAge(profileUpdate.getAge() );
        if( profileUpdate.getDate_birth() != null ) u_p.get().setDate_birth( profileUpdate.getDate_birth() );
        if( profileUpdate.getPassions() != null ) u_p.get().setPassions(profileUpdate.getPassions() );
        if( profileUpdate.getAbout_me() != null ) u_p.get().setAbout_me(profileUpdate.getAbout_me() );
        _iprofileRepo.save(u_p.get());

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult(u_p.get());
        return ResponseEntity.ok(result);

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Images>> uploadImages(@ModelAttribute ImageDTO images){

        Optional<User> u =  _iuserRepo.findById(images.getUser_id());
        ApiResponse<Images> result = new ApiResponse<>();

        Images img = new Images();

        img.setImage(_messageFireBaseService.uploadFile(images.getImage()) );
        img.setUser( u.orElse(null) );
        img.setImage2(_messageFireBaseService.uploadFile(images.getImage2() ) );
        img.setImage3(_messageFireBaseService.uploadFile(images.getImage3() ) );
        img.setImage4(_messageFireBaseService.uploadFile(images.getImage4() ) );
        img.setImage5(_messageFireBaseService.uploadFile(images.getImage5() ) );

        _imageRepo.save( img );



        result.setMessage("upload images successfully");
        result.setCode(200);
        result.setResult( img );

        return ResponseEntity.ok( result );






    }



}
