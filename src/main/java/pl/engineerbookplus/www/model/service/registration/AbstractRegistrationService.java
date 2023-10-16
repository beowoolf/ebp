package pl.engineerbookplus.www.model.service.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.dto.NewProfile;
import pl.engineerbookplus.www.security.Encoder;
import pl.engineerbookplus.www.service.mail.MailSenderService;

public abstract class AbstractRegistrationService {

    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private Encoder encoder;

    protected Person createNewPersonFromNewProfile(NewProfile newProfile, String password) {
        Person person = new Person();
        person.setName(newProfile.getName());
        person.setSurname(newProfile.getSurame());
        person.setUsername(newProfile.getUsername());
        person.setMail(newProfile.getMail());
        person.setPassword(encoder.encodePassword(password));
        person.setMaleGender(newProfile.isMaleGender());
        person.setBirthday(newProfile.getBirthday());
        person.setHideBirthday(true);
        person.setEnabled(false);
        return person;
    }

    protected ModelAndView getSendMailView(Person person, String password) {
        if (mailSenderService.sendPasswordForRegistration(person.getMail(), password))
            return new ModelAndView("registered", "person", person);
        return new ModelAndView("index", "message", "Wysłanie wiadomości e-mail nie powiodło się! Skontaktuj się z administratorem strony! <br />" + person.toString());
    }

}
