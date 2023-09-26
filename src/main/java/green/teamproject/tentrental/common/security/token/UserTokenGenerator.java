package green.teamproject.tentrental.common.security.token;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UserTokenGenerator {
    public static String generateUniqueToken() {
        UUID uuid = UUID.randomUUID(); // Generate a random UUID
        return uuid.toString(); // Convert UUID to a string
    }
}
