package com.semester.tinder.service;

import com.semester.tinder.dto.ReqRes;
import com.semester.tinder.entity.Role;
import com.semester.tinder.entity.User;
import com.semester.tinder.repository.IRoleRepo;
import com.semester.tinder.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private IUserRepo _iUserRepo; // related to jpa user

    @Autowired
    private JWTUtils _jwtUtils; // related to token

    @Autowired
    private PasswordEncoder _passwordEncoder; // related to passwordEncoder

    @Autowired
    private AuthenticationManager _authenticationManager; // related to quan ly phan quyen!
    //dc  cau hinh trong file "SecurityConfig" -> xu ly qua trinh xac thuc
    //no dinh nghia ra mot authenticate ma co the dc goi de xac thuc Authenticate object dc cung cap
    //

    @Autowired
    private IRoleRepo _iRoleRepo;

    public ReqRes signup( ReqRes registrationRequest ){
        ReqRes resp = new ReqRes();
        try {
            User user = new User();
            user.setFullname(registrationRequest.getName());
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(_passwordEncoder.encode(registrationRequest.getPassword()));
            user.setIsBlock(false);
           Optional<Role> role = _iRoleRepo.findById(1);
            role.ifPresent(user::setRole);
            User new_u = _iUserRepo.save( user );

                resp.setEmail( new_u.getEmail() );
                resp.setRole( new_u.getRole().getRole_name() );
                resp.setOurUser(new_u);
                resp.setMessage("user saved successfully");
                resp.setStatusCode(200);

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }

        return resp;

    }

    public ReqRes signin(ReqRes signinRequest){
            ReqRes response = new ReqRes();
            try {
                _authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
                //AuthenticationManager là một interface cốt lõi trong
                // Spring Security, nó định nghĩa một phương thức authenticate
                // mà có thể được gọi để xác thực Authentication object được cung cấp
                var n_user = _iUserRepo.findByEmail(signinRequest.getEmail()).orElseThrow();
                System.out.println("USER IS: " + n_user);
                 var jwt = _jwtUtils.generateToken(n_user);
                 var refreshToken = _jwtUtils.generateRefreshToken( new HashMap<>(), n_user);
                 response.setStatusCode(200);
                 response.setToken(jwt);
                 response.setRefreshToken( refreshToken );
                 response.setExpirationTime("24hour");
                 response.setMessage("successfully signed in");
            }catch (Exception e){
                response.setStatusCode(500);
                response.setError(e.getMessage());
            }
            return response;
    }

    public ReqRes refreshToken( ReqRes refreshTokenRequest ){
        ReqRes response = new ReqRes();
        String ourEmail = _jwtUtils.extractUsername(refreshTokenRequest.getToken());
        Optional<User> new_u = _iUserRepo.findByEmail( ourEmail );
        if(new_u.isPresent()){
            if( _jwtUtils.isTokenValid( refreshTokenRequest.getToken(), new_u.get()) ){
                var jwt = _jwtUtils.generateToken(new_u.get());
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken( refreshTokenRequest.getToken() );
                response.setExpirationTime("24hour");
                response.setMessage("successfully refresh token in");
            }
            return response;
        }
        response.setStatusCode(500);
        return response;

    }

}

/*
* if( role.isPresent() ){
*    user.setRole( role.get() )
* }
* Nó tương đương với:
* role.ifPresent(user::setRole)
*
*
*
* */