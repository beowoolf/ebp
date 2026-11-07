package pl.engineerbookplus.www.model.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.service.PersonsService;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonsService personsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personsService.searchPersonByUserName(username);

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (user != null)
            return new User(username, user.getPassword(), user.isEnabled(), true, true, true, authorities);
        throw new UsernameNotFoundException("Nie znaleziono użytkownika o loginie \"" + username + "\".");
    }

}
