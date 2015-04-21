package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;

/**
 * @author Johannes Graf - graf@synyx.de
 */
@RestController
public class TokenController {

    private final TokenStore tokenStore;

    @Autowired
    public TokenController(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @RolesAllowed("ROLE_CLIENT")
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ResponseEntity showClientTokens(OAuth2Authentication auth) {

        final String clientId = auth.getOAuth2Request().getClientId();
        final Collection<OAuth2AccessToken> tokensByClientId = tokenStore.findTokensByClientId(clientId);

        return ResponseEntity.ok(tokensByClientId);
    }

    @RolesAllowed("ROLE_CLIENT")
    @RequestMapping(value = "/token", method = RequestMethod.DELETE)
    public ResponseEntity expireToken(OAuth2Authentication auth) {

        final OAuth2AccessToken accessToken = tokenStore.getAccessToken(auth);
        tokenStore.removeAccessToken(accessToken);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
