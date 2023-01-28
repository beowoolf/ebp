package pl.engineerbookplus.www.model.service;

import org.springframework.web.servlet.ModelAndView;
import pl.engineerbookplus.www.model.dto.NewProfile;

public interface RegistrationService {

    ModelAndView registerNewProfile(NewProfile newProfile);

}
