package green.teamproject.tentrental.user.controller;

import green.teamproject.tentrental.user.repository.UserRepository;
import green.teamproject.tentrental.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class EmailConfirmController {

     @Autowired
     private UserService userService; // Example UserRepository

    @GetMapping("/check-email")
    public EmailCheckResponse checkEmail(@RequestParam String email) {
        boolean emailExists = userService.existsByEmail(email);
        return new EmailCheckResponse(emailExists);
    }

    // Define a response class
    private static class EmailCheckResponse {
        private final boolean exists;

        public EmailCheckResponse(boolean exists) {
            this.exists = exists;
        }

        public boolean isExists() {
            return exists;
        }
    }
}

