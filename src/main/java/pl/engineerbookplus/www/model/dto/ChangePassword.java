package pl.engineerbookplus.www.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChangePassword implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Hasło nie może być krótsze niż 6 znaków.")
    @Size(min = 6, max = 45, message = "Hasło nie może być krótsze niż 6 znaków.")
    private String currentPassword;
    @NotNull(message = "Hasło nie może być krótsze niż 6 znaków.")
    @Size(min = 6, max = 45, message = "Hasło nie może być krótsze niż 6 znaków.")
    private String password;
    @NotNull(message = "Potwierdzenie hasła nie może być krótsze niż 6 znaków.")
    @Size(min = 6, max = 45, message = "Potwierdzenie hasła nie może być krótsze niż 6 znaków.")
    private String confirmPassword;

}
