package com.semester.tinder.service;

import com.semester.tinder.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OurUserDetailService implements UserDetailsService {

    @Autowired
    private IUserRepo _iUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return _iUserRepo.findByEmail(username).orElseThrow();
    }
//loadUserByUsername de Spring Security sử dụng để tải thông tin người dùng khi thực hiện xác thực.





}
