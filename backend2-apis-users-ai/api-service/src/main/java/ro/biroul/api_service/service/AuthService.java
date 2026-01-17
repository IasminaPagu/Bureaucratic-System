package ro.biroul.api_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.biroul.api_service.dto.AuthResponse;
import ro.biroul.api_service.dto.LoginRequest;
import ro.biroul.api_service.dto.RegisterRequest;
import ro.biroul.api_service.entity.User;
import ro.biroul.api_service.repository.UserRepository;
import ro.biroul.api_service.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public void register(RegisterRequest req) {

        // 1) email unic
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Există deja un cont cu acest e-mail.");
        }

        // 2) confirmare parolă
        if (!req.getPassword().equals(req.getPasswordConfirm())) {
            throw new IllegalArgumentException("Parolele nu coincid.");
        }

        // 3) construim user + hash parolă
        User user = new User();
        user.setEmail(req.getEmail().trim().toLowerCase());
        user.setFirstName(req.getFirstName().trim());
        user.setLastName(req.getLastName().trim());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        // role / enabled / createdAt sunt setate în @PrePersist

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest req) {

        User user = userRepository
                .findByEmail(req.getEmail().trim().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("E-mail sau parolă incorecte."));

        if (!user.isEnabled()) {
            throw new IllegalArgumentException("Contul este dezactivat.");
        }

        boolean passwordOk = passwordEncoder.matches(
                req.getPassword(),
                user.getPassword());

        if (!passwordOk) {
            throw new IllegalArgumentException("E-mail sau parolă incorecte.");
        }

        // 🔐 JWT REAL
        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole());

        return new AuthResponse(token);
    }
}
