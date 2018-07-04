package com.cilleruelo.microservices.invoices.services.rest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * It enables to work as a resource server with an authorization server by OAuth token
 * 
 * @author francisco.cilleruelo
 *
 */
@Configuration
@EnableResourceServer 
public class InvoicesSecurityConfig extends ResourceServerConfigurerAdapter {

	@Value("${security.oauth2.resource.checkToken}")
	private String checkTokenUrl;

	@Value("${security.oauth2.resource.clientId}")
	private String oauthClientId;

	@Value("${security.oauth2.resource.secret}")
	private String oauthClientSecret;

	@Primary
	@Bean
	public RemoteTokenServices tokenService() {
		RemoteTokenServices tokenService = new RemoteTokenServices();
		tokenService.setCheckTokenEndpointUrl(checkTokenUrl);
		tokenService.setClientId(oauthClientId);
		tokenService.setClientSecret(oauthClientSecret);
		return tokenService;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenService()).resourceId("HermesInvoices");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and().authorizeRequests().anyRequest().permitAll();
	}

}
