package pl.engineerbookplus.www.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Encoder {

    @Autowired
    private PasswordEncoder encoder;

    public String encodePassword(String password) {
        return encoder.encode(password);
    }

    public boolean isPasswordMatches(String rawPass, String encodedPass) {
        return encoder.matches(rawPass, encodedPass);
    }

}
