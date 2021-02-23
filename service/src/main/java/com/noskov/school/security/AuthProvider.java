package com.noskov.school.security;

import com.noskov.school.dao.api.MedicalStaffDAO;
import com.noskov.school.persistent.MedicalStaffPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    MedicalStaffDAO medicalStaffDAO;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        MedicalStaffPO staffPO = medicalStaffDAO.getByEmail(email);
        if (staffPO == null){
            throw new UsernameNotFoundException("Staff no found during authentication");
            //throw new RuntimeException("Staff no found during authentication");
        }
        String password = authentication.getCredentials().toString();

        if(!bCryptPasswordEncoder.matches(password, staffPO.getPassword())){
            throw new BadCredentialsException("Passwords doesn't match");
            //throw new RuntimeException("Passwords doesn't match");
        }
        List<GrantedAuthority> authorityList = new ArrayList<>(staffPO.getAuthorities());
        return new UsernamePasswordAuthenticationToken(staffPO,  null, authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
