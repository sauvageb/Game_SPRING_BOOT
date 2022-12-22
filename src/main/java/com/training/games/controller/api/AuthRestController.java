package com.training.games.controller.api;

import com.training.games.controller.dto.SigninRequest;
import com.training.games.controller.dto.SignupRequest;
import com.training.games.controller.exception.UserAlreadyExistException;
import com.training.games.repository.entity.User;
import com.training.games.security.jwt.JwtResponse;
import com.training.games.security.jwt.JwtUtils;
import com.training.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest dto) {
        try {
            userService.signup(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody SigninRequest dto) throws Exception {
        // UsernamePasswordAuthenticationToken : implementation of the Authentication interface which specifies that the user wants to authenticate using a username and password.
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        // Filter Authentification for username passed in SigninRequest

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(auth);

            // The SecurityContextHolder is where Spring Security stores the details of who is authenticated.
            // Spring Security does not care how the SecurityContextHolder is populated.
            // If it contains a value, it is used as the currently authenticated user.
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JSON Web Token (JWT) (RFC 7519)
            String generatedToken = jwtUtils.generateJwt(authentication);

            // Get username from Authentication SecurityContextHolder
            User connectedUser = (User) authentication.getPrincipal();
            JwtResponse jwtResponse = new JwtResponse(connectedUser.getUsername(), generatedToken);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jwtResponse);

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

}
