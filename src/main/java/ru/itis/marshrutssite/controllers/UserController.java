package ru.itis.marshrutssite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itis.marshrutssite.RestSecurityJWT.jwt.details.UserDetailsImpl;
import ru.itis.marshrutssite.dto.TokenDto;
import ru.itis.marshrutssite.dto.UserDto;
import ru.itis.marshrutssite.models.Role;
import ru.itis.marshrutssite.models.State;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.services.UsersService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UsersService usersService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myProfile")
    public ResponseEntity<User> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = User.builder()
                .id(userDetails.getUserId())
                .name(userDetails.getUsername() + " " + userDetails.getSurname())
                .email(userDetails.getEmail())
                .build();
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @PreAuthorize(("isAuthenticated()"))
    @PostMapping("/editDataProfile")
    public ResponseEntity<String> edit(String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = User.builder()
                .id(userDetails.getUserId())
                .name(userDetails.getUsername() + " " + userDetails.getSurname())
                .email(userDetails.getEmail())
                .password(userDetails.getPassword())
                .hashPassword(userDetails.getHashPassword())
                .role(Role.USER)
                .state(State.CONFIRMED)
                .build();
        usersService.editProfile(user,email);
        return ResponseEntity.ok("edited");
    }
}
