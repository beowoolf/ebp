package pl.engineerbookplus.www.service.changer.login;

import org.springframework.stereotype.Service;
import pl.engineerbookplus.www.service.changer.AbstractChangerService;

@Service
public class LoginChangerServiceByEBP
        extends AbstractChangerService
        implements LoginChangerService {

    @Override
    public boolean sendTokenToChangeLogin(String to, String token, String oldLogin, String newLogin) {
        return mailSenderService.sendTokenToChangeLogin(to, token, oldLogin, newLogin);
    }

    @Override
    public boolean sendInformationAboutChangeLogin(String to, String oldLogin, String newLogin) {
        return mailSenderService.sendInformationAboutChangeLogin(to, oldLogin, newLogin);
    }

}
