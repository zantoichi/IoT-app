package com.blue.iotapp.service;

import com.blue.iotapp.model.Role;
import com.blue.iotapp.model.User;
import com.blue.iotapp.payload.SignUpRequest;
import com.blue.iotapp.repository.RoleRepository;
import com.blue.iotapp.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(SignUpRequest registration){

        Role role = roleRepository.findByName("ROLE_ADMIN").get();
        User user = new User();
        user.setName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
        user.setRoles(Collections.singleton(role));
        return userRepository.save(user);
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
