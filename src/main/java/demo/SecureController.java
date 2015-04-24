package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

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
