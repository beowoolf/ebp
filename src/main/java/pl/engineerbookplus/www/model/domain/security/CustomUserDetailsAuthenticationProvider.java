package pl.engineerbookplus.www.model.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.service.PersonsService;

import java.util.List;

@Component
public class CustomUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private PersonsService personsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @SuppressWarnings("deprecation")
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String password = (String) authentication.getCredentials();

        if (!StringUtils.hasText(password))
            throw new BadCredentialsException("Please enter password");

        Person user = personsService.searchPersonByUserName(username);

        if (user == null)
            throw new UsernameNotFoundException("User not found!");

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("Bad credentials!");

        if (!user.isEnabled())
            throw new BadCredentialsException("User disabled");

        final List<GrantedAuthority> auths = AuthorityUtils.createAuthorityList("ROLE_USER");

        return new User(username, password, user.isEnabled(), // isEnabled
                true, // account not expired
                true, // credentials not expired
                true, // account not locked
                auths);
    }

}
