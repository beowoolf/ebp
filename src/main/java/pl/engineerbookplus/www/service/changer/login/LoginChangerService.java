package pl.engineerbookplus.www.service.changer.login;

public interface LoginChangerService {

    boolean sendTokenToChangeLogin(String to, String token, String oldLogin, String newLogin);

    boolean sendInformationAboutChangeLogin(String to, String oldLogin, String newLogin);

}
