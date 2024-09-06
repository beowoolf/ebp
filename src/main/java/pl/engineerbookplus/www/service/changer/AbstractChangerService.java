package pl.engineerbookplus.www.service.changer;

import org.springframework.beans.factory.annotation.Autowired;
import pl.engineerbookplus.www.security.Encoder;
import pl.engineerbookplus.www.service.mail.MailSenderService;

public abstract class AbstractChangerService {

    @Autowired
    protected MailSenderService mailSenderService;
    @Autowired
    protected Encoder encoder;

}
