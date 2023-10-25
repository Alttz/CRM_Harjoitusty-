package s23.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


import s23.crm.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
			new AntPathRequestMatcher("/h2-console/**"),
			new AntPathRequestMatcher("/api/**") };
	
	private static final AntPathRequestMatcher[] ADMIN_LIST_URLS = {
			new AntPathRequestMatcher("/delete/**") };
			
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequest -> authorizeRequest
				// .requestMatchers("/index").permitAll()
				.requestMatchers(antMatcher("/css/**")).permitAll()
				.requestMatchers(WHITE_LIST_URLS).permitAll().requestMatchers(ADMIN_LIST_URLS).hasAuthority("ADMIN")
				.anyRequest().authenticated()).formLogin(formlogin -> formlogin
						// .loginPage("/login")
						.defaultSuccessUrl("/crm", true).permitAll())
				.logout(logout -> logout.permitAll())
				.csrf(csrf -> csrf.disable()); // not for production, only for development
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}