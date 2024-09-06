package pl.engineerbookplus.www.service.mail;

public interface MailSenderService {

    boolean sendPasswordForRegistration(String to, String password);

    boolean sendNewPassword(String to, String password);

    boolean sendTokenToChangeLogin(String to, String token, String oldLogin, String newLogin);

    boolean sendInformationAboutChangeLogin(String to, String oldLogin, String newLogin);

    boolean sendTokenToNewMail(String newMail, String token);

    boolean sendInformationAboutChangeMail(String oldMail, String newMail);

    boolean sendTokenToNewPassword(String to, String token);

    boolean sendInformationAboutNewPassword(String to, String newPassword);

    boolean sendTokenToDeleteProfile(String to, String token);

}
