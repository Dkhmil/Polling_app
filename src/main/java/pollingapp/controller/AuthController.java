package pollingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pollingapp.exception.AppException;
import pollingapp.model.Role;
import pollingapp.model.RoleName;
import pollingapp.model.User;
import pollingapp.payload.ApiResponse;
import pollingapp.payload.JwtAuthenticationResponse;
import pollingapp.payload.LoginRequest;
import pollingapp.payload.SingUpRequest;
import pollingapp.repository.RoleRepository;
import pollingapp.repository.UserRepository;
import pollingapp.security.JwtTokenProvider;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserNameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingUpRequest singUpRequest) {

        if (userRepository.existsByUsername(singUpRequest.getUserName())) {
            return new ResponseEntity(new ApiResponse(false, "Username already exist"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(singUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, " Email already exist"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(singUpRequest.getName(),
                singUpRequest.getUserName(),
                singUpRequest.getEmail(),
                singUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User Role not set"));
        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUserName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
