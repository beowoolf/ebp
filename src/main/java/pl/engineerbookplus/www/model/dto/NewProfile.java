package pl.engineerbookplus.www.model.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class NewProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Musisz podać swoje imię.")
    @Size(min = 1, max = 20, message = "Imię musi mieć długość z przedziału <1, 20> znaków.")
    private String name;
    @NotNull(message = "Musisz podać swoje aktualne nazwisko.")
    @Size(min = 1, max = 45, message = "Nazwisko musi mieć długość z przedziału <1, 45> znaków.")
    private String surame;
    @NotNull(message = "Musisz podać nazwę użytkownika.")
    @Size(min = 5, max = 45, message = "Nazwa użytkownika musi mieć długość z przedziału <5, 45> znaków.")
    private String username;
    @NotNull(message = "Musisz podać poprawny adres e-mail")
    @Size(min = 5, max = 45, message = "Adres e-mail musi mieć długość z przedziału <5, 45> znaków.")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Niepoprawny adres e-mail.")
    private String mail;
    @NotNull(message = "Musisz podać czy jesteś mężczyzną.")
    private boolean maleGender = true;
    @Past(message = "Data musi być z przeszłości.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthday;
    @NotNull(message = "Musisz wyrazić zgodę na przetwarzanie danych osobowych.")
    @AssertTrue(message = "Musisz wyrazić zgodę na przetwarzanie danych osobowych.")
    private boolean privacyPolicy;
    @NotNull(message = "Musisz zaakceptować regulamin serwisu.")
    @AssertTrue(message = "Musisz zaakceptować regulamin serwisu.")
    private boolean acceptTerms;

    @Override
    public String toString() {
        return "Pomyślnie zarejestrowano profil o następujących informacjach:<br />"
                + "Imię: " + name + "<br />"
                + "Nazwisko: " + surame + "<br />"
                + "Nazwa użytkownika: " + username + "<br />"
                + "Adres e-mail: " + mail + "<br />"
//                + "Hasło: " + password + "<br />"
//                + "Potwierdzenie hasła: " + confirmPassword + "<br />"
                + "Data urodzenia: " + birthday.getDate() + "." + (birthday.getMonth() + 1) + "." + (birthday.getYear() + 1900) + "<br />"
                + "Mężczyzna: " + (maleGender ? "tak" : "nie") + "<br />"
                + "<br />Hasło do Twojego konta zostało wysłane na adres podany w formularzu rejestracyjnym.<br />"
                + "";
    }

}
