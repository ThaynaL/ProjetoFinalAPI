//package org.serratec.backend.controller;
//
//import org.serratec.backend.security.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//
//    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest loginRequest) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//            new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha());
//
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        return tokenService.generateToken(userDetails);
//    }
//
//    // DTO interno só pra login
//    public record LoginRequest(String email, String senha) {}
//}