package pl.engineerbookplus.www.model.service.registration;

import org.springframework.web.servlet.ModelAndView;
import pl.engineerbookplus.www.model.dto.NewProfile;

public interface RegistrationService {

    ModelAndView registerNewProfile(NewProfile newProfile);

}
