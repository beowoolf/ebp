package pl.engineerbookplus.www.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class AbstractMailSenderService {

    @Autowired
    protected transient JavaMailSender javaMailSender;

}
