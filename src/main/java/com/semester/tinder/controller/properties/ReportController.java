package com.semester.tinder.controller.properties;

import com.semester.tinder.dto.request.Profile.ProfileRequest;
import com.semester.tinder.dto.request.Profile.ProfileUpdate;
import com.semester.tinder.dto.request.Report.ReportRequest;
import com.semester.tinder.dto.response.ApiResponse;
import com.semester.tinder.entity.Profile;
import com.semester.tinder.entity.Report;
import com.semester.tinder.entity.User;
import com.semester.tinder.repository.IReportRepo;
import com.semester.tinder.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/user/report")
public class ReportController {

    @Autowired
    private IReportRepo _ireportRepo;

    @Autowired
    private IUserRepo _iuserRepo;

    @GetMapping("/getId")
    public ResponseEntity<ApiResponse<Report>> getProfile(@RequestParam int reportId){

        ApiResponse<Report> result = new ApiResponse<>();

        Optional<Report> p = _ireportRepo.findById(reportId);

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
    public ResponseEntity<ApiResponse<String>> deleteP(@RequestParam int reportId){

        ApiResponse<String> result = new ApiResponse<>();

        Optional<Report> p = _ireportRepo.findById(reportId);

        if( p.isEmpty() ){
            result.setMessage("error! data not found");
            result.setCode(404);
            return ResponseEntity.ok(result);
        }

        _ireportRepo.delete(p.get());

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult("delete profile id: " + reportId + " successfully");
        return ResponseEntity.ok(result);

    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Report>> createNew(@RequestBody ReportRequest reportRequest){

        Optional<User> u_report =  _iuserRepo.findById(reportRequest.getUser_report());
        Optional<User> u_accused =  _iuserRepo.findById(reportRequest.getUser_accused());

        Report new_re = new Report();

        new_re.setContent( reportRequest.getContent() );
        new_re.setReport_time( new Date());
        new_re.setUser_report( u_report.orElse(null) );
        new_re.setUser_accused( u_accused.orElse(null) );

        _ireportRepo.save(new_re);

        ApiResponse<Report> result = new ApiResponse<>();

        result.setMessage("successfully");
        result.setCode(200);
        result.setResult( new_re );
        return ResponseEntity.ok(result);

    }





}
