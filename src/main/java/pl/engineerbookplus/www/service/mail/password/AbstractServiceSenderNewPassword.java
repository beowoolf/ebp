package pl.engineerbookplus.www.service.mail.password;

import org.springframework.beans.factory.annotation.Autowired;
import pl.engineerbookplus.www.security.Encoder;
import pl.engineerbookplus.www.service.mail.MailSenderService;

public abstract class AbstractServiceSenderNewPassword {

    @Autowired
    protected MailSenderService mailSenderService;
    @Autowired
    protected Encoder encoder;

}
