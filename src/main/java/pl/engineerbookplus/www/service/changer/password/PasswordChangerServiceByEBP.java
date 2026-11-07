package pl.engineerbookplus.www.service.changer.password;

import org.springframework.stereotype.Service;
import pl.engineerbookplus.www.service.changer.AbstractChangerService;

@Service
public class PasswordChangerServiceByEBP
        extends AbstractChangerService
        implements PasswordChangerService {

    @Override
    public boolean sendTokenToNewPassword(String to, String token) {
        return mailSenderService.sendTokenToNewPassword(to, token);
    }

    @Override
    public boolean sendInformationAboutNewPassword(String to, String newPassword) {
        return mailSenderService.sendInformationAboutNewPassword(to, newPassword);
    }

}
