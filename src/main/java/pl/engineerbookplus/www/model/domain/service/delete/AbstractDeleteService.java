package pl.engineerbookplus.www.model.domain.service.delete;

import org.springframework.beans.factory.annotation.Autowired;
import pl.engineerbookplus.www.model.domain.service.PersonsService;
import pl.engineerbookplus.www.security.Encoder;
import pl.engineerbookplus.www.service.mail.MailSenderService;

public class AbstractDeleteService {

    @Autowired
    protected MailSenderService mailSenderService;

    @Autowired
    protected PersonsService personsService;

    @Autowired
    protected Encoder encoder;

}
