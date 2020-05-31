package ru.itis.marshrutssite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.marshrutssite.dto.TokenDto;
import ru.itis.marshrutssite.dto.UserDto;
import ru.itis.marshrutssite.services.SignInService;


@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(signInService.signIn(userDto));
    }
}
