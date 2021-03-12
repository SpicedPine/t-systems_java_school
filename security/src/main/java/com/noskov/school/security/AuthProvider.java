package com.noskov.school.security;

import com.noskov.school.dao.api.MedicalStaffDAO;
import com.noskov.school.persistent.MedicalStaffPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthProvider.class);

    @Autowired
    MedicalStaffDAO medicalStaffDAO;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        MedicalStaffPO staffPO = medicalStaffDAO.getByEmail(email);
        if (staffPO == null){
            LOGGER.info("Staff no found during authentication");
            throw new UsernameNotFoundException("Staff no found during authentication");
        }
        String password = authentication.getCredentials().toString();

        if(!bCryptPasswordEncoder.matches(password, staffPO.getPassword())){
            LOGGER.info("Passwords doesn't match during authentication");
            throw new BadCredentialsException("Passwords doesn't match");
        }
        List<GrantedAuthority> authorityList = new ArrayList<>(staffPO.getAuthorities());

        LOGGER.info("Staff was authenticated");
        return new UsernamePasswordAuthenticationToken(staffPO,  null, authorityList);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
