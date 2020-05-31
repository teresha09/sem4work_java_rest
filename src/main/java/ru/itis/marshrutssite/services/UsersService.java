package ru.itis.marshrutssite.services;

import ru.itis.marshrutssite.models.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();
    void editProfile(User user,String email);
}
