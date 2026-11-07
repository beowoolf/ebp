package pl.engineerbookplus.www.model.service.registration;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pl.engineerbookplus.www.model.domain.service.PersonsService;
import pl.engineerbookplus.www.model.dto.NewProfile;

@Service
public class RegistrationServiceByEBP
        extends AbstractRegistrationService
        implements RegistrationService {

    @Autowired
    private PersonsService personsService;

    @Override
    public ModelAndView registerNewProfile(NewProfile newProfile) {
        String password = RandomStringUtils.randomAlphanumeric(8);
        return getSendMailView(
                personsService.addRecord(
                        createNewPersonFromNewProfile(
                                newProfile,
                                password
                        )
                ),
                password
        );
    }

}
