package com.example.bankingsystem.service.impl;

import com.example.bankingsystem.converter.UserConverter;
import com.example.bankingsystem.exception.ServiceOperationAlreadyDeletedException;
import com.example.bankingsystem.exception.ServiceOperationCanNotCreateException;
import com.example.bankingsystem.exception.ServiceOperationNotFoundException;
import com.example.bankingsystem.model.dto.request.UserCreateRequestDTO;
import com.example.bankingsystem.model.entity.User;
import com.example.bankingsystem.repository.UserRepository;
import com.example.bankingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 21.05.2022
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public void addUser(UserCreateRequestDTO userCreateRequestDTO) {
        User u = userRepository.findUserByEMailAddress(userCreateRequestDTO.mail());
        if (Objects.isNull(u)) {
            User user = userConverter.toUser(userCreateRequestDTO);
            userRepository.save(user);
        } else
            throw new ServiceOperationCanNotCreateException.UserIsAlreadyCreatedException("Email is already taken for a user ");
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationNotFoundException.UserNotFoundException("User can not found"));
        if (user.isDeleted()) {
            throw new ServiceOperationAlreadyDeletedException.UserAlreadyDeletedException("Customer is already deleted");
        }
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAllUsersByDeleteStatusByJPQL(false)
                .stream()
                .toList();
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository
                .findUserByEMailAddress(email);
        if (Objects.isNull(user)) {
            return null;
        }
        return user;
    }

}
