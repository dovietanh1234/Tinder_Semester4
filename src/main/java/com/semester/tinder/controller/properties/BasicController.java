package com.semester.tinder.controller.properties;

import com.semester.tinder.dto.request.Basic.BasicRequest;
import com.semester.tinder.dto.request.Profile.ProfileRequest;
import com.semester.tinder.dto.request.Profile.ProfileUpdate;
import com.semester.tinder.dto.response.ApiResponse;
import com.semester.tinder.entity.Basic;
import com.semester.tinder.entity.Profile;
import com.semester.tinder.entity.User;
import com.semester.tinder.repository.IBasicRepo;
import com.semester.tinder.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/basic") // can token
public class BasicController {
    @Autowired
    private IUserRepo _iuserRepo;

    @Autowired
    private IBasicRepo _ibasicRepo;

    @GetMapping("/getId")
    public ResponseEntity<ApiResponse<Basic>> get(@RequestParam int basicId){

        ApiResponse<Basic> result = new ApiResponse<>();

        Optional<Basic> p = _ibasicRepo.findById(basicId);

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
    public ResponseEntity<ApiResponse<String>> deleteP(@RequestParam int basicId){

        ApiResponse<String> result = new ApiResponse<>();

        Optional<Basic> p = _ibasicRepo.findById(basicId);

        if( p.isEmpty() ){
            result.setMessage("error! data not found");
            result.setCode(404);
            return ResponseEntity.ok(result);
        }

        _ibasicRepo.delete(p.get());

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult("delete profile id: " + basicId + " successfully");
        return ResponseEntity.ok(result);

    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Basic>> createNew(@RequestBody BasicRequest basicRequest){

        Optional<User> u =  _iuserRepo.findById(basicRequest.getUser_id());

        Basic p = new Basic();

        p.setEducation(basicRequest.getEducation());
        p.setUser(u.orElse(null));
        p.setZodiac(basicRequest.getZodiac());
        p.setCommunication_style(basicRequest.getCommunication_style());
        p.setFamily_plans(basicRequest.getFamily_plans());
        p.setLove_style(basicRequest.getLove_style());
        p.setPersonality_style(basicRequest.getPersonality_style());


        _ibasicRepo.save(p);

        ApiResponse<Basic> result = new ApiResponse<>();

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult( p );

        return ResponseEntity.ok(result);
    }


    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Basic>> update(@RequestBody BasicRequest basicRequest){


        // tim id ->
        Optional<Basic> u_p = _ibasicRepo.findById(basicRequest.getId());
        ApiResponse<Basic> result = new ApiResponse<>();

        if( u_p.isEmpty() ){
            result.setMessage("ERROR! NOT FOUND");
            result.setCode(404);
            return ResponseEntity.ok(result);
        }

        if (basicRequest.getEducation() != null) u_p.get().setEducation(basicRequest.getEducation());
        if (basicRequest.getZodiac() != null) u_p.get().setZodiac(basicRequest.getZodiac());
        if (basicRequest.getCommunication_style() != null) u_p.get().setCommunication_style(basicRequest.getCommunication_style());
        if (basicRequest.getFamily_plans() != null) u_p.get().setFamily_plans(basicRequest.getFamily_plans());
        if (basicRequest.getLove_style() != null) u_p.get().setLove_style(basicRequest.getLove_style());
        if (basicRequest.getPersonality_style() != null) u_p.get().setPersonality_style(basicRequest.getPersonality_style());

        _ibasicRepo.save(u_p.get());

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult(u_p.get());
        return ResponseEntity.ok(result);

    }


}
