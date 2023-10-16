package pl.engineerbookplus.www.model.service.password;

import pl.engineerbookplus.www.model.domain.Person;
import pl.engineerbookplus.www.model.domain.service.PersonsService;

public interface ServiceSenderNewPassword {

    boolean sendNewPassword(PersonsService personsService, Person person);

}
