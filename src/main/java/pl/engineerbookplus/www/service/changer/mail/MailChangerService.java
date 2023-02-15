package pl.engineerbookplus.www.service.changer.mail;

public interface MailChangerService {

    boolean sendTokenToNewMail(String newMail, String token);

    boolean sendInformationAboutChangeMail(String oldMail, String newMail);

}
