package pl.engineerbookplus.www.model.service.password;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.service.PersonsService;

@Service
public class ServiceSenderNewPasswordByEBP
        extends AbstractServiceSenderNewPassword
        implements ServiceSenderNewPassword {

    @Override
    public boolean sendNewPassword(PersonsService personsService, Person person) {
        String password = RandomStringUtils.randomAlphanumeric(8);
        personsService.updatePassword(person, encoder.encodePassword(password));
        return mailSenderService.sendNewPassword(person.getMail(), password);
    }

}
