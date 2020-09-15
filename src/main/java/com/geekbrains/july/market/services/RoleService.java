package com.geekbrains.july.market.services;

import com.geekbrains.july.market.entities.Role;
import com.geekbrains.july.market.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRolesRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findOneByName(name);
    }
}
