package pl.engineerbookplus.www.model.domain.service.delete;

import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

public interface DeleteService {

    ModelAndView sendTokenToDeActivationProfileIfPasswordIsValid(String passToDelete, Authentication auth);

    ModelAndView processDeActivationProfileIfTokensIsValid(String tokenFromLink, String token, Authentication auth);

    ModelAndView processDeleteProfileIfTokensAndPasswordIsValid(String tokenFromLink, String tokenFromLinkTwo, String passToDelete, Authentication auth);

}
