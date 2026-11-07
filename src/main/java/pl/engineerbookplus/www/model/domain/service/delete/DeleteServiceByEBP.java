package pl.engineerbookplus.www.model.domain.service.delete;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pl.engineerbookplus.www.model.domain.Person;

@Service
public class DeleteServiceByEBP
        extends AbstractDeleteService
        implements DeleteService {

    @Override
    public ModelAndView sendTokenToDeActivationProfileIfPasswordIsValid(String passToDelete, Authentication auth) {
        if (auth instanceof AnonymousAuthenticationToken)
            return new ModelAndView("index", "message", "<font color='red'>Musisz być zalogowany, aby przeglądać tę stronę w ten sposób</font><br />");
        if (passToDelete == null)
            return new ModelAndView("index", "message", "<font color='red'>Nie podano hasła.</font><br /><a href='/profile/manage'>Wróć na stronę Zarządzania Profilem</a><br />");
        Person person = personsService.searchPersonByUserName(auth.getName());
        if (person == null)
            return new ModelAndView("index", "message", "<font color='red'>Nie znalazłem Ciebie w bazie danych.</font><br />");
        if (!(encoder.isPasswordMatches(passToDelete, person.getPassword())))
            return new ModelAndView("index", "message", "<font color=\"red\">Hasło, które podałeś jako Twoje obecne hasło jest niewłaściwe!</font><br /><a href='/profile/manage'>Wróć na stronę zarządzania profilem.</a>");
        if (mailSenderService.sendTokenToDeleteProfile(person.getMail(), person.getPassword().substring(15, 30))) {
            ModelAndView mv = new ModelAndView("deactivation");
            mv.addObject("url", person.getPassword().substring(35, 55));
            return mv;
        } else
            return new ModelAndView("index", "message", "<font color='red'>Coś poszło nie tak przy wysyłaniu tokenu.</font><br><a href='/profile/manage'>Wróć na stronę zarządzania profilem</a>.<br />");
    }

    @Override
    public ModelAndView processDeActivationProfileIfTokensIsValid(String tokenFromLink, String token, Authentication auth) {
        if (auth instanceof AnonymousAuthenticationToken)
            return new ModelAndView("index", "message", "<font color=\"red\">Musisz być zalogowany, aby przeglądać tę stronę w ten sposób</font><br />");
        if (token == null)
            return new ModelAndView("index", "message", "<font color='red'>Nie podano tokenu.</font><br /><a href='/profile/manage'>Wróć na stronę Zarządzania Profilem</a><br />");
        Person person = personsService.searchPersonByUserName(auth.getName());
        if (person == null)
            return new ModelAndView("index", "message", "<font color=\"red\">Nie znalazłem Ciebie w bazie danych!</font><br /><a href='/profile/manage'>Wróć na stronę zarządzania profilem.</a>");
        if ((!(tokenFromLink.equalsIgnoreCase(person.getPassword().substring(35, 55)))) || (!(token.equalsIgnoreCase(person.getPassword().substring(15, 30)))))
            return new ModelAndView("index", "message", "<font color='red'>Tokeny się nie zgadzają.</font><br /><a href='/profile/manage'>Wróć na stronę zarządzania profilem.</a>");
        person.setEnabled(false);
        if (personsService.editRecord(person) != null) {
            ModelAndView mv = new ModelAndView("delete");
            mv.addObject("first", tokenFromLink);
            mv.addObject("second", token);
            return mv;
        } else
            return new ModelAndView("index", "message", "<font color='red'>Coś poszło nie tak przy pierwszym etapie usuwania profilu.</font><br><a href='/profile/manage'>Wróć na stronę zarządzania profilem</a>.<br />");
    }

    @Override
    public ModelAndView processDeleteProfileIfTokensAndPasswordIsValid(String tokenFromLink, String tokenFromLinkTwo, String passToDelete, Authentication auth) {
        if (auth instanceof AnonymousAuthenticationToken)
            return new ModelAndView("index", "message", "<font color=\"red\">Musisz być zalogowany, aby przeglądać tę stronę w ten sposób</font><br />");
        if (passToDelete == null)
            return new ModelAndView("index", "message", "<font color='red'>Nie podano hasła.</font><br /><a href='/profile/manage'>Wróć na stronę Zarządzania Profilem</a><br />");
        Person person = personsService.searchPersonByUserName(auth.getName());
        if (person == null)
            return new ModelAndView("index", "message", "<font color=\"red\">Nie znalazłem Ciebie w bazie danych!</font><br /><a href='/profile/manage'>Wróć na stronę zarządzania profilem.</a>");
        if ((!(tokenFromLink.equalsIgnoreCase(person.getPassword().substring(35, 55)))) || (!(tokenFromLinkTwo.equalsIgnoreCase(person.getPassword().substring(15, 30)))))
            return new ModelAndView("index", "message", "<font color='red'>Tokeny się nie zgadzają.</font><br /><a href='/profile/manage'>Wróć na stronę zarządzania profilem.</a>");
        if (encoder.isPasswordMatches(passToDelete, person.getPassword())) {
            if (personsService.eraseRecord(person) != null) {
                SecurityContextHolder.clearContext();
                return new ModelAndView("index", "message", "<font color'green'>Twój profil został usunięty poprawnie.</font><br /><a href='/'>Wróć na stronę główną</a><br />");
            } else
                return new ModelAndView("index", "message", "<font color='red'>Nie usunąłem użytkownika, bo nie znalazłem go w bazie danych.</font><br />");
        } else
            return new ModelAndView("index", "message", "<font color='red'>Twój profil nie został usunięty, bo wprowadzono błędnie hasło do profilu.</font><br><a href='/profile/manage'>Wróć na stronę zarządzania profilem</a>.<br />");
    }

}
