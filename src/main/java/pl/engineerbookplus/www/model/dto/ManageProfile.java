package pl.engineerbookplus.www.model.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class ManageProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Musisz podać login lub adres e-mail do konta.")
    @Size(min = 1, max = 45, message = "Login lub adres e-mail do konta musi mieć długość z przedziału <1, 45> znaków.")
    private String mailOrLogin;
    @NotNull(message = "Musisz podać czy chcesz resetować hasło czy nie.")
    private boolean resetPassword;
    @NotNull(message = "Musisz podać czy chcesz aktywować konto.")
    private boolean activation;

}
