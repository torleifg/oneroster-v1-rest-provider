package no.iktsenteret.pifu.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import no.iktsenteret.pifu.security.oauth.OneRosterConsumerDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final OneRosterConsumerDetailsService oneRosterConsumerDetailsService;

	public WebSecurityConfig(OneRosterConsumerDetailsService oneRosterConsumerDetailsService) {
		this.oneRosterConsumerDetailsService = oneRosterConsumerDetailsService;
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/h2-console/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(protectedResourceProcessingFilter(), BasicAuthenticationFilter.class).authorizeRequests()
				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	private Filter protectedResourceProcessingFilter() {
		ProtectedResourceProcessingFilter filter = new ProtectedResourceProcessingFilter();
		filter.setConsumerDetailsService(oneRosterConsumerDetailsService);
		filter.setIgnoreMissingCredentials(false);
		return filter;
	}
}