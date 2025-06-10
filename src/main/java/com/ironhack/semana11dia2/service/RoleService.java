package com.ironhack.semana11dia2.service;


import com.ironhack.semana11dia2.exception.NotFoundException;
import com.ironhack.semana11dia2.model.Role;
import com.ironhack.semana11dia2.model.User;
import com.ironhack.semana11dia2.repository.RoleRepository;
import com.ironhack.semana11dia2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor // para crear el constructor que usamos para la inyección de dependencias
public class RoleService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Role save(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        if (roleRepository.findByName(role.getName()) != null) {
            throw new IllegalArgumentException("Role already exists: " + role.getName());
        }
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("User");
        }

        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new NotFoundException("Role");
        }

        List<Role> userRoles = user.getRoles();
        userRoles.add(role);
        userRepository.save(user);
    }
}
