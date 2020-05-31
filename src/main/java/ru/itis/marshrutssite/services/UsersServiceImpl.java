package ru.itis.marshrutssite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.repositories.UsersRepository;

import java.util.List;
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void editProfile(User user, String email) {
        user.setEmail(email);
        usersRepository.save(user);
    }


}
