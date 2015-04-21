package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Token;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;

/**
 * @author Johannes Graf - graf@synyx.de
 */
@RestController
public class SecureController {

    @RequestMapping("/secure")
    @RolesAllowed("ROLE_CLIENT")
    public String get() {
        return "{\"message\":\"this is a secure message!\"}";
    }

    @RequestMapping("/secure/role_admin")
    @RolesAllowed("ROLE_ADMIN")
    public String getRoleAdmin() {
        return "{\"message\":\"this is a secure message for ROLE_ADMIN only!\"}";
    }

}
