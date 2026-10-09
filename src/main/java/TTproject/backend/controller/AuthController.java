package TTproject.backend.controller;

import TTproject.backend.model.AdminUser;
import TTproject.backend.repository.AdminUserRepository;
import TTproject.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Automatically inserts default admin on startup if no admin exists
    @Bean
    CommandLineRunner initAdmin(AdminUserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                AdminUser admin = new AdminUser("admin", encoder.encode("admin123"));
                repo.save(admin);
                System.out.println(">>> Default Admin initialized (username: admin, password: admin123)");
            }
        };
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<AdminUser> admin = adminUserRepository.findByUsername(username);

        if (admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword())) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of("token", token, "username", username));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}