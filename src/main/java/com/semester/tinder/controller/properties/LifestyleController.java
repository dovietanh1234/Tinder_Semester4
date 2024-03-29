package com.semester.tinder.controller.properties;

import com.semester.tinder.dto.request.Profile.ProfileRequest;
import com.semester.tinder.dto.request.Profile.ProfileUpdate;
import com.semester.tinder.dto.request.lifestyle.LifestyleRequest;
import com.semester.tinder.dto.response.ApiResponse;
import com.semester.tinder.entity.Lifestyle;
import com.semester.tinder.entity.Profile;
import com.semester.tinder.entity.User;
import com.semester.tinder.repository.ILifestyleRepo;
import com.semester.tinder.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/lifestyle") // can token
public class LifestyleController {

    @Autowired
    private IUserRepo _iuserRepo;

    private ILifestyleRepo _ilifestyleRepo;

    @GetMapping("/getId")
    public ResponseEntity<ApiResponse<Lifestyle>> getId(@RequestParam int id){

        ApiResponse<Lifestyle> result = new ApiResponse<>();

        Optional<Lifestyle> p = _ilifestyleRepo.findById(id);

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
    public ResponseEntity<ApiResponse<String>> deleteP(@RequestParam int id){

        ApiResponse<String> result = new ApiResponse<>();

        Optional<Lifestyle> p = _ilifestyleRepo.findById(id);

        if( p.isEmpty() ){
            result.setMessage("error! data not found");
            result.setCode(404);
            return ResponseEntity.ok(result);
        }

        _ilifestyleRepo.delete(p.get());

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult("delete profile id: " + id + " successfully");
        return ResponseEntity.ok(result);

    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Lifestyle>> createNew(@RequestBody LifestyleRequest lifestyleRequest){

        Optional<User> u =  _iuserRepo.findById(lifestyleRequest.getUser_id());

        Lifestyle p = new Lifestyle();

        p.setPet(lifestyleRequest.getPet() );
        p.setSmoking(lifestyleRequest.getSmoking());
        p.setDrinking(lifestyleRequest.getDrinking());
        p.setWorkout(lifestyleRequest.getWorkout());
        p.setUser(u.orElse(null));
        p.setDietary_preference(lifestyleRequest.getDietary_preference());
        p.setSleeping_habits(lifestyleRequest.getSleeping_habits());
        p.setSocial_media(lifestyleRequest.getSocial_media());

        _ilifestyleRepo.save(p);

        ApiResponse<Lifestyle> result = new ApiResponse<>();

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult( p );
        return ResponseEntity.ok(result);

    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Lifestyle>> update(@RequestBody LifestyleRequest lifestyleRequest){


        // tim id ->
        Optional<Lifestyle> u_p = _ilifestyleRepo.findById(lifestyleRequest.getId());
        ApiResponse<Lifestyle> result = new ApiResponse<>();

        if( u_p.isEmpty() ){
            result.setMessage("ERROR! NOT FOUND");
            result.setCode(404);
            return ResponseEntity.ok(result);
        }

        if (lifestyleRequest.getPet() != null) u_p.get().setPet(lifestyleRequest.getPet());
        if (lifestyleRequest.getSmoking() != null) u_p.get().setSmoking(lifestyleRequest.getSmoking());
        if (lifestyleRequest.getDrinking() != null) u_p.get().setDrinking(lifestyleRequest.getDrinking());
        if (lifestyleRequest.getWorkout() != null) u_p.get().setWorkout(lifestyleRequest.getWorkout());
        if (lifestyleRequest.getDietary_preference() != null) u_p.get().setDietary_preference(lifestyleRequest.getDietary_preference());
        if (lifestyleRequest.getSleeping_habits() != null) u_p.get().setSleeping_habits(lifestyleRequest.getSleeping_habits());
        if (lifestyleRequest.getSocial_media() != null) u_p.get().setSocial_media(lifestyleRequest.getSocial_media());


        _ilifestyleRepo.save(u_p.get());

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult(u_p.get());
        return ResponseEntity.ok(result);

    }











}
