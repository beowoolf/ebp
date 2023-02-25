package pl.engineerbookplus.www.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ChangePassword implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message="Hasło nie może być krótsze niż 6 znaków.")
    @Size(min=6, max=45, message="Hasło nie może być krótsze niż 6 znaków.")
    private String currentPassword;
    @NotNull(message="Hasło nie może być krótsze niż 6 znaków.")
    @Size(min=6, max=45, message="Hasło nie może być krótsze niż 6 znaków.")
    private String password;
    @NotNull(message = "Potwierdzenie hasła nie może być krótsze niż 6 znaków.")
    @Size(min=6, max=45, message="Potwierdzenie hasła nie może być krótsze niż 6 znaków.")
    private String confirmPassword;

    /**
     * @return the currentPassword
     */
    public String getCurrentPassword() {
        return currentPassword;
    }

    /**
     * @param currentPassword the currentPassword to set
     */
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of confirmPassword
     *
     * @return the value of confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Set the value of confirmPassword
     *
     * @param confirmPassword new value of confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
