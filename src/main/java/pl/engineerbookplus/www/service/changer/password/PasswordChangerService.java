package pl.engineerbookplus.www.service.changer.password;

public interface PasswordChangerService {

    boolean sendTokenToNewPassword(String to, String token);

    boolean sendInformationAboutNewPassword(String to, String newPassword);

}
