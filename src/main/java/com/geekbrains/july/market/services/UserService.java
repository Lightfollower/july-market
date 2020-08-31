package com.geekbrains.july.market.services;

import com.geekbrains.july.market.entities.Role;
import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.entities.dtos.SystemUser;
import com.geekbrains.july.market.repositories.RoleRepository;
import com.geekbrains.july.market.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRolesService(RoleService roleService) {
        this.roleService = roleService;
    }

    public Optional<User> findByPhone(String phone) {
        return userRepository.findOneByPhone(phone);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByPhone(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    @Transactional
    public User saveOrUpdate(User user) {
        User temp = userRepository.findById(user.getId()).get();
        user.setPhone(temp.getPhone());
        user.setPassword(temp.getPassword());
        user.setFirstName(temp.getFirstName());
        user.setLastName(temp.getLastName());
        return userRepository.save(user);
    }

    @Transactional
    public User save(SystemUser systemUser) {
        User user = new User();
        findByPhone(systemUser.getPhone()).ifPresent((u) -> {
            throw new RuntimeException("User with phone " + systemUser.getPhone() + " is already exist");
        });
        user.setPhone(systemUser.getPhone());
        user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        user.setFirstName(systemUser.getFirstName());
        user.setLastName(systemUser.getLastName());
        user.setEmail(systemUser.getEmail());
        user.setRoles(Arrays.asList(roleService.findByName("ROLE_CUSTOMER")));
        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void deleteById(int id) {
        userRepository.deleteById((long)id);
    }
}