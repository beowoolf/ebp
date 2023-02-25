package pl.engineerbookplus.www.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ManageProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Musisz podać login lub adres e-mail do konta.")
    @Size(min=1, max=45, message="Login lub adres e-mail do konta musi mieć długość z przedziału <1, 45> znaków.")
    private String mailOrLogin;
    @NotNull(message = "Musisz podać czy chcesz resetować hasło czy nie.")
    private boolean resetPassword;
    @NotNull(message = "Musisz podać czy chcesz aktywować konto.")
    private boolean activation;

    /**
     * @return the mailOrLogin
     */
    public String getMailOrLogin() {
        return mailOrLogin;
    }

    /**
     * @param mailOrLogin the mailOrLogin to set
     */
    public void setMailOrLogin(String mailOrLogin) {
        this.mailOrLogin = mailOrLogin;
    }

    /**
     * @return the resetPassword
     */
    public boolean isResetPassword() {
        return resetPassword;
    }

    /**
     * @param resetPassword the resetPassword to set
     */
    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    /**
     * @return the activation
     */
    public boolean isActivation() {
        return activation;
    }

    /**
     * @param activation the activation to set
     */
    public void setActivation(boolean activation) {
        this.activation = activation;
    }

}
