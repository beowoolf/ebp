package pl.engineerbookplus.www.service.mail;

import org.springframework.core.io.UrlResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.net.MalformedURLException;

@Service
public class MailSenderServiceByEBP
        extends AbstractMailSenderService
        implements MailSenderService {

    @Override
    public boolean sendPasswordForRegistration(String to, String password) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@engineerbookplus.com.pl");
            helper.setTo(to);
            helper.setSubject("Rejestracja w Engineerbook+");
            helper.setText("<html><body>"
                    + "<img src='cid:ebpLogo'><br />"
                    + "<h1>Rejestracja</h1><br />"
                    + "Twoje hasło to: " + password
                    + "</body></html>", true);
            UrlResource logo = new UrlResource("https://localhost:8181/images/logo.png");
            helper.addInline("ebpLogo", logo);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException | MalformedURLException ex) {
            return false;
        }
    }

    @Override
    public boolean sendNewPassword(String to, String password) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@engineerbookplus.com.pl");
            helper.setTo(to);
            helper.setSubject("Nowe hasło do konta profilu Engineerbook+");
            helper.setText("<html><body>"
                    + "<img src='cid:ebpLogo'><br />"
                    + "<h1>Resetowanie hasła</h1><br />"
                    + "Twoje nowe hasło to: " + password
                    + "</body></html>", true);
            UrlResource logo = new UrlResource("https://localhost:8181/images/logo.png");
            helper.addInline("ebpLogo", logo);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException | MalformedURLException ex) {
            return false;
        }
    }

    @Override
    public boolean sendTokenToChangeLogin(String to, String token, String oldLogin, String newLogin) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@engineerbookplus.com.pl");
            helper.setTo(to);
            helper.setSubject("Twój token do zmiany loginu profilu Engineerbook+");
            helper.setText("<html><body>"
                    + "<img src='cid:ebpLogo'><br />"
                    + "<h1>Token do zmiany loginu</h1><br />"
                    + "Twój token do zmiany loginu \"" + oldLogin + "\" na login \"" + newLogin + "\" dla profilu to: " + token
                    + "</body></html>", true);
            UrlResource logo = new UrlResource("https://localhost:8181/images/logo.png");
            helper.addInline("ebpLogo", logo);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException | MalformedURLException ex) {
            return false;
        }
    }

    @Override
    public boolean sendInformationAboutChangeLogin(String to, String oldLogin, String newLogin) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@engineerbookplus.com.pl");
            helper.setTo(to);
            helper.setSubject("Zmiana loginu profilu Engineerbook+");
            helper.setText("<html><body>"
                    + "<img src='cid:ebpLogo'><br />"
                    + "<h1>Zmiana loginu profilu Engineerbook+</h1><br />"
                    + "Twój stary login: " + oldLogin + "<br />Twój nowy login: " + newLogin
                    + "</body></html>", true);
            UrlResource logo = new UrlResource("https://localhost:8181/images/logo.png");
            helper.addInline("ebpLogo", logo);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException | MalformedURLException ex) {
            return false;
        }
    }

    @Override
    public boolean sendTokenToNewMail(String newMail, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@engineerbookplus.com.pl");
            helper.setTo(newMail);
            helper.setSubject("Twój token do zmiany adresu e-mail profilu Engineerbook+");
            helper.setText("<html><body>"
                    + "<img src='cid:ebpLogo'><br />"
                    + "<h1>Token do zmiany adresu e-mail</h1><br />"
                    + "Twój token do zmiany adresu e-mail dla profilu to: " + token
                    + "</body></html>", true);
            UrlResource logo = new UrlResource("https://localhost:8181/images/logo.png");
            helper.addInline("ebpLogo", logo);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException | MalformedURLException ex) {
            return false;
        }
    }

    @Override
    public boolean sendInformationAboutChangeMail(String oldMail, String newMail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@engineerbookplus.com.pl");
            helper.setBcc(new String[]{oldMail, newMail});
            helper.setSubject("Zmiana adresu e-mail profilu Engineerbook+");
            helper.setText("<html><body>"
                    + "<img src='cid:ebpLogo'><br />"
                    + "<h1>Zmiana adresu e-mail profilu Engineerbook+</h1><br />"
                    + "Twój stary adres e-mail: " + oldMail + "<br />Twój nowy adres e-mail: " + newMail
                    + "</body></html>", true);
            UrlResource logo = new UrlResource("https://localhost:8181/images/logo.png");
            helper.addInline("ebpLogo", logo);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException | MalformedURLException ex) {
            return false;
        }
    }

    @Override
    public boolean sendTokenToNewPassword(String to, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@engineerbookplus.com.pl");
            helper.setTo(to);
            helper.setSubject("Twój token do zmiany obecnego hasła profilu Engineerbook+");
            helper.setText("<html><body>"
                    + "<img src='cid:ebpLogo'><br />"
                    + "<h1>Token do zmiany adresu e-mail</h1><br />"
                    + "Twój token do zmiany obecnego hasła profilu to: " + token
                    + "</body></html>", true);
            UrlResource logo = new UrlResource("https://localhost:8181/images/logo.png");
            helper.addInline("ebpLogo", logo);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException | MalformedURLException ex) {
            return false;
        }
    }

    @Override
    public boolean sendInformationAboutNewPassword(String to, String newPassword) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@engineerbookplus.com.pl");
            helper.setTo(to);
            helper.setSubject("Zmiana obecnego hasła do konta profilu Engineerbook+");
            helper.setText("<html><body>"
                    + "<img src='cid:ebpLogo'><br />"
                    + "<h1>Zmiana obecnego hasła do konta profilu Engineerbook+</h1><br />"
                    + "Potwierdzenie zmiany hasła na hasło: " + newPassword
                    + "</body></html>", true);
            UrlResource logo = new UrlResource("https://localhost:8181/images/logo.png");
            helper.addInline("ebpLogo", logo);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException | MalformedURLException ex) {
            return false;
        }
    }

    @Override
    public boolean sendTokenToDeleteProfile(String to, String token) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("noreply@engineerbookplus.com.pl");
            helper.setTo(to);
            helper.setSubject("Twój token do przeprowadzenia usunięcia konta profilu Engineerbook+");
            helper.setText("<html><body>"
                    + "<img src='cid:ebpLogo'><br />"
                    + "<h1>Token do przeprowadzenia usunięcia konta profilu Engineerbook+</h1><br />"
                    + "Twój token do przeprowadzenia usunięcia konta profilu Engineerbook+ jest następujący: " + token
                    + "</body></html>", true);
            UrlResource logo = new UrlResource("https://localhost:8181/images/logo.png");
            helper.addInline("ebpLogo", logo);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException | MalformedURLException ex) {
            return false;
        }
    }

}
