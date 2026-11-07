package pl.engineerbookplus.www.service.changer.mail;

import org.springframework.stereotype.Service;
import pl.engineerbookplus.www.service.changer.AbstractChangerService;

@Service
public class MailChangerServiceByEBP
        extends AbstractChangerService
        implements MailChangerService {

    @Override
    public boolean sendTokenToNewMail(String newMail, String token) {
        return mailSenderService.sendTokenToNewMail(newMail, token);
    }

    @Override
    public boolean sendInformationAboutChangeMail(String oldMail, String newMail) {
        return mailSenderService.sendInformationAboutChangeMail(oldMail, newMail);
    }

}
