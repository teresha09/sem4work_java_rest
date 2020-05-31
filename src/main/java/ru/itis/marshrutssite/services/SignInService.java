package ru.itis.marshrutssite.services;

import ru.itis.marshrutssite.dto.TokenDto;
import ru.itis.marshrutssite.dto.UserDto;


public interface SignInService {
    TokenDto signIn(UserDto userDto);
}
