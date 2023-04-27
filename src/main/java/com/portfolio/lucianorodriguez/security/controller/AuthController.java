package com.portfolio.lucianorodriguez.security.controller;

import com.portfolio.lucianorodriguez.security.dto.JwtDto;
import com.portfolio.lucianorodriguez.security.dto.LoginUserDto;
import com.portfolio.lucianorodriguez.security.dto.NewUserDto;
import com.portfolio.lucianorodriguez.security.entity.Role;
import com.portfolio.lucianorodriguez.security.entity.User;
import com.portfolio.lucianorodriguez.security.enums.RoleType;
import com.portfolio.lucianorodriguez.security.jwt.JwtProvider;
import com.portfolio.lucianorodriguez.security.service.RoleService;
import com.portfolio.lucianorodriguez.security.service.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import com.portfolio.lucianorodriguez.utility.Message;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"", "http://localhost:4200"})
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Message("Campos con errores o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsUserByUsername(newUserDto.getUsername())) {
            return new ResponseEntity(new Message("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsUserByEmail(newUserDto.getEmail())) {
            return new ResponseEntity(new Message("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        }
        User user = new User(newUserDto.getName(), newUserDto.getSurname(), newUserDto.getUsername(),
                newUserDto.getEmail(), passwordEncoder.encode(newUserDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByType(RoleType.ROLE_USER).get());
        
        if (newUserDto.getRoles().contains("admin")) {
            roles.add(roleService.getRoleByType(RoleType.ROLE_ADMIN).get());
        }
        
        user.setRoles(roles);
        userService.saveUser(user);

        return new ResponseEntity(new Message("Usuario creado correctamente"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())            
            return new ResponseEntity(new Message("Campos con errores"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(), loginUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> listUser() {
        List<User> users = userService.listUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
