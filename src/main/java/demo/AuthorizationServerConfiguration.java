package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;


/**
 * @author Johannes Graf - graf@synyx.de
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    //@Primary
    public TokenStore tokenStore() {
        //return new InMemoryTokenStore();
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    //@Primary
    public DefaultTokenServices tokenServices(TokenStore tokenStore) {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setAccessTokenValiditySeconds(-1);
        defaultTokenServices.setTokenStore(tokenStore);
        return defaultTokenServices;
    }

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DefaultTokenServices tokenServices;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenServices(tokenServices).tokenStore(tokenStore);
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws  Exception {

        clients.inMemory()
                .withClient("user")
                    .authorizedGrantTypes("client_credentials")
                    .authorities("ROLE_CLIENT")
                    .scopes("read")
                    .secret("password")
                .and()
                .withClient("admin")
                    .authorizedGrantTypes("client_credentials")
                    .authorities("ROLE_CLIENT", "ROLE_ADMIN")
                    .scopes("read")
                    .secret("password");
    }
}
