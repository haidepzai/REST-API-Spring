package de.oette.lecture.A05;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/hash-secured")
@Validated
public class HashSecurityController {

    private static final String SALT = "drewpdrjq3perjo3423fnl3423cer34";

    @GetMapping
    public String getAccess(@RequestParam @NotNull String user,
                            @RequestParam @NotNull String hash,
                            @RequestParam @NotNull long timestampValidUntil) throws CustomAccessDeniedException {

        long now = System.currentTimeMillis();

        if (timestampValidUntil < now) {
            throw new CustomAccessDeniedException("No longer valid");
        }

        String calculatedHash = calculateHash(user, SALT, String.valueOf(timestampValidUntil));
        if (calculatedHash.equals(hash)) {
            return "Access granted";
        } else {
            throw new CustomAccessDeniedException("Wrong hash");
        }

    }
    //String... mehrere Strings
    private String calculateHash(String... strings) {
        String concat = String.join("#", strings);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(concat.getBytes(StandardCharsets.UTF_8));
            return String.copyValueOf(Hex.encode(digest));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        long valid60Seconds = System.currentTimeMillis() + 60 * 1000;
        System.out.println(valid60Seconds);

        String calculateHash = new HashSecurityController()
                .calculateHash("Max", SALT, String.valueOf(valid60Seconds));
        System.out.println(calculateHash);

    }

}
